package com.lab365.app.pcp.controller.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ClassroomUpdateDTO {
    private String name;
    private Long courseId;
    private List<Long> teacherIds;
    private List<Long> subjectIds;
}