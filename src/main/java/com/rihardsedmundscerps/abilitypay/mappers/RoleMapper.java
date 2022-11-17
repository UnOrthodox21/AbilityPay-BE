package com.rihardsedmundscerps.abilitypay.mappers;

import com.rihardsedmundscerps.abilitypay.items.RoleItem;
import com.rihardsedmundscerps.abilitypay.models.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleItem toRoleItem(Role role) {

        if (role == null) {
            return null;
        }

        return new RoleItem.Builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public Role toRole(RoleItem roleItem) {

        if (roleItem == null) {
            return null;
        }

        Role role = new Role();

        role.setId(roleItem.getId());
        role.setName(roleItem.getName());

        return role;
    }

}
