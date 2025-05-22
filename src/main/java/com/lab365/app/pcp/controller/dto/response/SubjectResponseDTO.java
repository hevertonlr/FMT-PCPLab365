package com.lab365.app.pcp.controller.dto.response;

import lombok.Data;

@Data
public class SubjectResponseDTO {
    private Long id;
    private String name;
    private CourseDTO course;
}
