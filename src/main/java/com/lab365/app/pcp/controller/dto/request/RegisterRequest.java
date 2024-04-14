package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.infra.validation.ValidPassword;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull String username,
        @ValidPassword String password
) {
    public User toEntity(){
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        return entity;
    }
}
