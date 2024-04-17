package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Course;

import java.util.List;
import java.util.stream.Collectors;

public record CourseResponse(Long id, String name) {
    public static CourseResponse fromEntity(Course entity) {
        return new CourseResponse(entity.getId(), entity.getName());

    }

    public static List<CourseResponse> fromEntity(List<Course> entities) {
        return entities.stream().map(CourseResponse::fromEntity).collect(Collectors.toList());
    }
}
