package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Subject;

public record SubjectResponse(Long id, String name, Long courseId) implements IGenericResponseDTO<Subject,SubjectResponse>{

    @Override
    public SubjectResponse fromEntity(Subject entity) {
        return new SubjectResponse(entity.getId(),entity.getName(),entity.getCourse().getId());
    }
}
