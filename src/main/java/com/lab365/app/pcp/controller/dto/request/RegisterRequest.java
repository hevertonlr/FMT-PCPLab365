package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.Role;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.enums.RolesEnum;
import com.lab365.app.pcp.infra.validation.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull String username,
        @ValidPassword String password,
        @NotNull @Email String email,
        String image
) {
    public User toEntity() {
        Role role = new Role();
        role.setName(RolesEnum.DEACTIVATED.toString());
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setImage(image);
        entity.setRole(role);
        return entity;
    }
}
