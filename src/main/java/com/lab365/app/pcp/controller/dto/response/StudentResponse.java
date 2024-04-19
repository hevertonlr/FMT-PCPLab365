package com.lab365.app.pcp.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record StudentResponse(Long id,
                              String name,
                              @JsonFormat(pattern = "dd/MM/yyyy")
                              @JsonSerialize(using = LocalDateSerializer.class)
                              @JsonDeserialize(using = LocalDateDeserializer.class)
                              LocalDate birthday,
                              String classroomName,
                              String login) {
    public static StudentResponse fromEntity(Student entity) {
        return new StudentResponse(entity.getId(),
                entity.getName(),
                entity.getBirthday(),
                entity.getClassroom().getName(),
                entity.getUser().getUsername());

    }

    public static List<StudentResponse> fromEntity(List<Student> entities) {
        return entities.stream().map(StudentResponse::fromEntity).collect(Collectors.toList());
    }
}
