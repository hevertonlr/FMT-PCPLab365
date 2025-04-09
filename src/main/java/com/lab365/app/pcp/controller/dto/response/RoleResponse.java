package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Role;
import org.hibernate.Hibernate;

public record RoleResponse(String name) {
    public static RoleResponse fromEntity(Role entity) {
        if(entity == null || !Hibernate.isInitialized(entity)) return null;
        return new RoleResponse(entity.getName());
    }
}
