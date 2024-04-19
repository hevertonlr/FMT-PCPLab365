package com.lab365.app.pcp.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Grade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record GradeResponse(
        Long id,
        BigDecimal value,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate date,
        String studentName,
        String teacherName,
        String subjectName) {
    public static GradeResponse fromEntity(Grade entity) {
        return new GradeResponse(entity.getId(),
                entity.getValue(),
                entity.getDate(),
                entity.getStudent().getName(),
                entity.getTeacher().getName(),
                entity.getSubject().getName());
    }

    public static List<GradeResponse> fromEntity(List<Grade> entities) {
        return entities.stream().map(GradeResponse::fromEntity).collect(Collectors.toList());
    }
}
