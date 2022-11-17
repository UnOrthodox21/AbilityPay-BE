package com.rihardsedmundscerps.abilitypay.repositories;

import com.rihardsedmundscerps.abilitypay.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
