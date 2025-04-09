package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Course;

public record CourseResponse(Long id, String name) implements IGenericResponseDTO<Course,CourseResponse> {

    @Override
    public CourseResponse fromEntity(Course entity) {
        return new CourseResponse(entity.getId(), entity.getName());
    }
}
