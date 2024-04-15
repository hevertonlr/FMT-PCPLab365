package com.lab365.app.pcp.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record TeacherResponse(Long id,
                              String name,
                              @JsonFormat(pattern = "dd/MM/yyyy")
                              @JsonSerialize(using = LocalDateSerializer.class)
                              @JsonDeserialize(using = LocalDateDeserializer.class)
                              LocalDate entryDate,
                              String profile,
                              String login) {
    public static TeacherResponse fromEntity(Teacher entity) {
        return new TeacherResponse(entity.getId(),
                entity.getName(),
                entity.getEntryDate(),
                entity.getUser().getRole().getName(),
                entity.getUser().getUsername());

    }

    public static List<TeacherResponse> fromEntity(List<Teacher> entities) {
        return entities.stream().map(TeacherResponse::fromEntity).collect(Collectors.toList());
    }
}
