package com.rihardsedmundscerps.abilitypay.repositories;

import com.rihardsedmundscerps.abilitypay.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findUserDetailsById(Long id);
    UserDetails findUserDetailsByUsername(String username);
    UserDetails findUserDetailsByJwt(String jwt);

}
