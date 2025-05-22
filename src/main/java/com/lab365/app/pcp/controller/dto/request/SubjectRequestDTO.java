package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubjectRequestDTO {
        @NotBlank(message = "Subject name is required")
        private String name;
        @NotNull(message = "Course ID is required")
        private Long courseid;
}
