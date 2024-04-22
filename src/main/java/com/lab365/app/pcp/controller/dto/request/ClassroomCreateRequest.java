package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.Classroom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomCreateRequest(@NotBlank String name,
                                     @NotNull Long teacherid,
                                     @NotNull Long courseid) {
    public Classroom toEntity() {
        Classroom entity = new Classroom();
        entity.setName(name);
        return entity;
    }
}
