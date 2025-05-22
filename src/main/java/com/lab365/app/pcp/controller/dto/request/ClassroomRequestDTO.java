package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ClassroomRequestDTO {
        @NotBlank(message = "Classroom name is required")
        private String name;
        @NotNull(message = "Course ID is required")
        private Long courseid;
        private List<Long> teacherIds;
        private List<Long> subjectIds;
}
