package com.rihardsedmundscerps.abilitypay.controllers;

import com.rihardsedmundscerps.abilitypay.auth.AuthenticationRequest;
import com.rihardsedmundscerps.abilitypay.auth.AuthenticationResponse;
import com.rihardsedmundscerps.abilitypay.auth.JwtUtil;
import com.rihardsedmundscerps.abilitypay.items.BankAccountItem;
import com.rihardsedmundscerps.abilitypay.items.UserDetailsItem;
import com.rihardsedmundscerps.abilitypay.mappers.UserDetailsMapper;
import com.rihardsedmundscerps.abilitypay.models.UserDetails;
import com.rihardsedmundscerps.abilitypay.services.BankAccountService;
import com.rihardsedmundscerps.abilitypay.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/api/users")
public class UserDetailsController {

    private final MyUserDetailsService userDetailsService;
    private final BankAccountService bankAccountService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final UserDetailsMapper userDetailsMapper;

    @Autowired
    public UserDetailsController(MyUserDetailsService userDetailsService,
                                 BankAccountService bankAccountService,
                                 UserDetailsMapper userDetailsMapper,
                                 AuthenticationManager authenticationManager,
                                 JwtUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.bankAccountService = bankAccountService;
        this.userDetailsMapper = userDetailsMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/getAllUsers/{password}")
    public List<UserDetailsItem> getAllUsers(@PathVariable("password") String password) {
        if (userDetailsService.checkAdminPassword(password)) {
            return userDetailsService.getAllUserDetails();
        }
        return null;
    }

    @GetMapping(path = "{username}")
    public UserDetailsItem getUserDetailsByUsername(@PathVariable("username") String username) {
        UserDetails foundUserDetails = userDetailsService.loadUserByUsername(username);
        UserDetailsItem foundUserDetailsItem = userDetailsMapper.toUserDetailsItem(foundUserDetails);
        return foundUserDetailsItem;
    }

    @GetMapping(path = "/getUserByJwt/{jwt}")
    public UserDetailsItem getUserDetailsByJwt(@PathVariable("jwt") String jwt) {
        return userDetailsService.findUserDetailsByJwt(jwt);
    }

    @PostMapping
    public void addNewUserDetails(@RequestBody UserDetailsItem userDetailsItem) {
        userDetailsService.addNewUserDetails(userDetailsItem);
    }


    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable("username") String username){
        userDetailsService.deleteUserDetailsByUsername(username);
    }

    @PutMapping(value="/{username}")
    public void replaceItem (@RequestBody UserDetailsItem userDetailsItem, @PathVariable String username){
        userDetailsService.changeUserDetailsData(userDetailsItem, username);
    }

    @PostMapping(path = "/register")
    public void registerNewUser(@RequestBody UserDetailsItem userDetailsItem) {
        UserDetailsItem registerUserDetailsItem = userDetailsService.addNewUserDetails(userDetailsItem);
        String newAccountNumber = "ABLTY" + Math.round(Math.floor(Math.random() * (99999999 - 10000000 + 1)) + 10000000);
        BankAccountItem newBankAccountItem = new BankAccountItem.Builder().balance(100.00).number(newAccountNumber).type("Primary").userId(registerUserDetailsItem.getId()).build();
        bankAccountService.addNewBankAccount(newBankAccountItem);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}
