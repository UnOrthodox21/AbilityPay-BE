package com.rihardsedmundscerps.abilitypay.services;

import com.rihardsedmundscerps.abilitypay.items.RoleItem;
import com.rihardsedmundscerps.abilitypay.mappers.RoleMapper;
import com.rihardsedmundscerps.abilitypay.models.Role;
import com.rihardsedmundscerps.abilitypay.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleItem> getAllRoles() {
        List<Role> allRoles = roleRepository.findAll();

        return allRoles.stream()
                .map(roleMapper::toRoleItem)
                .collect(Collectors.toList());
    }

    public Optional<RoleItem> getRoleById(Long roleId) {
        Optional<Role> foundRole = roleRepository.findById(roleId);
        RoleItem foundRoleItem = roleMapper.toRoleItem(foundRole.get());
        return Optional.of(foundRoleItem);
    }

    public void addNewRole(RoleItem roleItem) {
        Role roleToSave = roleMapper.toRole(roleItem);
        roleRepository.save(roleToSave);
    }

    public void deleteRole(Long roleId) {
        boolean exists = roleRepository.existsById(roleId);
        if (!exists) {
            throw new IllegalStateException(("Role with id " + roleId + " does not exist."));
        }
        roleRepository.deleteById(roleId);
    }


}
