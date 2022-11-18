package com.rihardsedmundscerps.abilitypay.controllers;

import com.rihardsedmundscerps.abilitypay.items.RoleItem;
import com.rihardsedmundscerps.abilitypay.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleItem> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping(path="{roleId}")
    public Optional<RoleItem> getRoleById(@PathVariable("roleId") Long roleId) {
        return roleService.getRoleById(roleId);
    }

    @PostMapping
    public void addNewRole(@RequestBody RoleItem roleItem) {
        roleService.addNewRole(roleItem);
    }

    @DeleteMapping(path="{roleId}")
    public void deleteRole(@PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);
    }

}
