package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Grade;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GradeResponse(Long id, BigDecimal value, LocalDate date, Long studentId, Long teacherId,
                            Long subjectId) implements IGenericResponseDTO<Grade,GradeResponse>{

    @Override
    public GradeResponse fromEntity(Grade entity) {
        return new GradeResponse(entity.getId(), entity.getValue(), entity.getDate(), entity.getStudent().getId(),
                entity.getTeacher().getId(), entity.getSubject().getId());
    }
}