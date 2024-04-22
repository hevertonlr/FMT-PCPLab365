package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.Course;
import jakarta.validation.constraints.NotBlank;

public record CourseRequest(@NotBlank String name) {
    public Course toEntity() {
        Course course = new Course();
        course.setName(name);
        return course;
    }
}
