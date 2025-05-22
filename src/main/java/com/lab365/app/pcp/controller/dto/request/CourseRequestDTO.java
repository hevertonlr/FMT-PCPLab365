package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequestDTO {
    @NotBlank(message = "Course name is required")
    private String name;
}
