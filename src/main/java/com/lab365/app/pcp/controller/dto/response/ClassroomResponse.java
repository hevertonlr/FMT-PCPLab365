package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Classroom;

import java.util.List;
import java.util.stream.Collectors;

public record ClassroomResponse(Long id, String name, String teacherName, String courseName) {
    public static ClassroomResponse fromEntity(Classroom entity) {
        return new ClassroomResponse(entity.getId(),
                entity.getName(),
                entity.getTeacher().getName(),
                entity.getCourse().getName());
    }

    public static List<ClassroomResponse> fromEntity(List<Classroom> entities) {
        return entities.stream().map(ClassroomResponse::fromEntity).collect(Collectors.toList());
    }
}
