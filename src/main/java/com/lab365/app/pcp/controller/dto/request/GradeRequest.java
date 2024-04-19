package com.lab365.app.pcp.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Grade;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GradeRequest(
        @NotNull
        BigDecimal value,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate date,
        @NotNull Long studentid,
        @NotNull Long teacherid,
        @NotNull Long subjectid
) {
    public Grade toEntity() {
        Grade entity = new Grade();
        entity.setValue(value);
        entity.setDate(date);
        return entity;
    }
}
