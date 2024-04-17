package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Subject;

import java.util.List;
import java.util.stream.Collectors;

public record SubjectResponse(Long id, String name, String courseName) {
    public static SubjectResponse fromEntity(Subject entity) {
        return new SubjectResponse(entity.getId(), entity.getName(), entity.getCourse().getName());
    }

    public static List<SubjectResponse> fromEntity(List<Subject> entities) {
        return entities.stream().map(SubjectResponse::fromEntity).collect(Collectors.toList());
    }
}
