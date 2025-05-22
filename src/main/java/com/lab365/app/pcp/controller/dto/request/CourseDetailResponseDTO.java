package com.lab365.app.pcp.controller.dto.request;

import lombok.Data;

@Data
public class CourseDetailResponseDTO {
        private Long id;
        private String name;
        private Integer classroomsCount;
        private Integer subjectsCount;

}
