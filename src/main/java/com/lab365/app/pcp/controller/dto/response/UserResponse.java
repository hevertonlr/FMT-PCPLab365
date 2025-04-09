package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Role;
import com.lab365.app.pcp.datasource.entity.User;
import org.hibernate.Hibernate;

import java.util.Optional;

public record UserResponse(Long id, String username, String name, String email, String image,
        Optional<String> profile) {

    public static UserResponse fromEntity(User entity) {
        if (entity == null || !Hibernate.isInitialized(entity))
            return null;
        return new UserResponse(entity.getId(), entity.getUsername(), entity.getName(),
                entity.getEmail(), entity.getImage(), Optional.ofNullable(entity.getRole()).map(Role::getName));
    }
}
