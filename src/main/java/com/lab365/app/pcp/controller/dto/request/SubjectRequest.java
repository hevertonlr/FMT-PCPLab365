package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.Subject;
import jakarta.validation.constraints.NotBlank;

public record SubjectRequest(@NotBlank String name, Long courseid) {
    public Subject toEntity() {

        Subject entity = new Subject();
        entity.setName(name);
        return entity;
    }
}
