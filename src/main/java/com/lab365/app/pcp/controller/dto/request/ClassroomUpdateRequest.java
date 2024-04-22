package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.Classroom;
import jakarta.validation.constraints.NotBlank;

public record ClassroomUpdateRequest(@NotBlank String name) {
    public Classroom toEntity() {
        Classroom entity = new Classroom();
        entity.setName(name);
        return entity;
    }
}
