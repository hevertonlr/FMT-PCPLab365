package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.Role;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.enums.RolesEnum;
import com.lab365.app.pcp.infra.validation.annotation.ValidPassword;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull String username,
        @ValidPassword String password
) {
    public User toEntity() {
        Role role = new Role();
        role.setName(RolesEnum.ADM.toString());
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setRole(role);
        return entity;
    }
}
