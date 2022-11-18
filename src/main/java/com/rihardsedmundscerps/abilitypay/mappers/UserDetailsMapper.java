package com.rihardsedmundscerps.abilitypay.mappers;

import com.rihardsedmundscerps.abilitypay.items.UserDetailsItem;
import com.rihardsedmundscerps.abilitypay.models.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetailsItem toUserDetailsItem(UserDetails userDetails) {

        if (userDetails == null) {
            return null;
        }

        return new UserDetailsItem.Builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .address(userDetails.getAddress())
                .phone(userDetails.getPhone())
                .password(userDetails.getPassword())
                .roles(userDetails.getRoles())
                .enabled(userDetails.isEnabled())
                .authorities(userDetails.getAuthorities())
                .image(userDetails.getImage())
                .jwt(userDetails.getJwt())
                .build();
    }

    public UserDetails toUserDetails(UserDetailsItem userDetailsItem) {

        if (userDetailsItem == null) {
            return null;
        }

        UserDetails userDetails = new UserDetails();

        userDetails.setId(userDetailsItem.getId());
        userDetails.setUsername(userDetailsItem.getUsername());
        userDetails.setEmail(userDetailsItem.getEmail());
        userDetails.setFirstName(userDetailsItem.getFirstName());
        userDetails.setLastName(userDetailsItem.getLastName());
        userDetails.setAddress(userDetailsItem.getAddress());
        userDetails.setPhone(userDetailsItem.getPhone());
        userDetails.setPassword(userDetailsItem.getPassword());
        userDetails.setRoles(userDetailsItem.getRoles());
        userDetails.setEnabled(userDetailsItem.isEnabled());
        userDetails.setAuthorities(userDetailsItem.getAuthorities());
        userDetails.setImage(userDetailsItem.getImage());
        userDetails.setJwt(userDetailsItem.getJwt());

        return userDetails;
    }

}
