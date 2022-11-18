package com.rihardsedmundscerps.abilitypay.services;

import com.rihardsedmundscerps.abilitypay.items.UserDetailsItem;
import com.rihardsedmundscerps.abilitypay.mappers.UserDetailsMapper;
import com.rihardsedmundscerps.abilitypay.models.UserDetails;
import com.rihardsedmundscerps.abilitypay.repositories.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsMapper userDetailsMapper;

    public MyUserDetailsService(UserDetailsRepository userRepository, UserDetailsMapper userDetailsMapper) {
        this.userDetailsRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    public List<UserDetailsItem> getAllUserDetails() {

        List<UserDetails> allUserDetails = userDetailsRepository.findAll();

        return allUserDetails.stream()
                .map(userDetailsMapper::toUserDetailsItem)
                .collect(Collectors.toList());

    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findUserDetailsByUsername(username);
    }

    public UserDetailsItem loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        UserDetails foundUserDetails = userDetailsRepository.findUserDetailsByUsername(username);
        UserDetailsItem foundUserDetailsItem = userDetailsMapper.toUserDetailsItem(foundUserDetails);
        return foundUserDetailsItem;
    }


    public UserDetailsItem findUserDetailsByJwt(String jwt) throws UsernameNotFoundException {
        UserDetails foundUserDetails = userDetailsRepository.findUserDetailsByJwt(jwt);
        UserDetailsItem foundUserDetailsItem = userDetailsMapper.toUserDetailsItem(foundUserDetails);
        return foundUserDetailsItem;
    }

    public boolean checkAdminPassword(String password){
        String adminPassword=("gillbates");
        if(password.equals(adminPassword)){
            return true;
        }
        else {
            return  false;
        }
    }

    public UserDetailsItem findUserDetailsByUsername(String userToFind){
        List<UserDetailsItem> newList = getAllUserDetails();
        UserDetailsItem userDetails = null;
        String name = userToFind;
        for(int i=0;i< newList.size();i++){
            if(newList.get(i).getUsername().equals(name)){
                userDetails = newList.get(i);
                System.out.println(userDetails.getUsername());
                break;
            }
        }
        return userDetails;
    }

    public void deleteUserDetailsByUsername(String username){
        UserDetails userDetailsItemToDelete = loadUserByUsername(username);
        userDetailsRepository.delete(userDetailsItemToDelete);
    }

    public void addNewUserDetails(UserDetailsItem userDetailsItem) {
        UserDetails userDetailsToSave = userDetailsMapper.toUserDetails(userDetailsItem);
        userDetailsRepository.save(userDetailsToSave);
    }

    public void changeUserDetailsData(UserDetailsItem userDetailsItem, String username){

        UserDetails userDetailsToPut = loadUserByUsername(username);

        if(userDetailsItem.getEmail() != null){
            userDetailsToPut.setEmail(userDetailsItem.getEmail());
        }

        if(userDetailsItem.getUsername() != null){
            userDetailsToPut.setUsername(userDetailsItem.getUsername());
        }

        if(userDetailsItem.getFirstName() != null){
            userDetailsToPut.setFirstName(userDetailsItem.getFirstName());
        }

        if(userDetailsItem.getLastName() != null){
            userDetailsToPut.setLastName(userDetailsItem.getLastName());
        }

        if(userDetailsItem.getPhone() != 0){
            userDetailsToPut.setPhone(userDetailsItem.getPhone());
        }

        if(userDetailsItem.getAddress() != null){
            userDetailsToPut.setAddress(userDetailsItem.getAddress());
        }

        if(userDetailsItem.getPassword() != null){
            userDetailsToPut.setPassword(userDetailsItem.getPassword());
        }

        if(userDetailsItem.getRoles() != null){
            userDetailsToPut.setRoles(userDetailsItem.getRoles());
        }

        if(userDetailsItem.getImage() != null){
            userDetailsToPut.setImage(userDetailsItem.getImage());
        }

        if(userDetailsItem.getJwt() != null){
            userDetailsToPut.setJwt(userDetailsItem.getJwt());
        }

        userDetailsToPut.setEnabled(userDetailsItem.isEnabled());

        userDetailsRepository.save(userDetailsToPut);
    }

}
