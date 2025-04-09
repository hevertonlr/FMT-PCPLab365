package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.infra.validation.annotation.ValidPassword;

public record UserUpdateRequest(Long id,
                                String username,
                                String name,
                                String email,
                                String image,
                                @ValidPassword String password) {

    public User toEntity() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setName(name);
        user.setEmail(email);
        user.setImage(image);
        user.setPassword(password);
        return user;
    }
}
