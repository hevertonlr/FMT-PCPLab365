package com.lab365.app.pcp.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Teacher;

import java.time.LocalDate;

public record TeacherUpdateRequest(
        String name,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate entryDate
) {
    public Teacher toEntity() {
        Teacher entity = new Teacher();
        entity.setName(name);
        entity.setEntryDate(entryDate);
        return entity;
    }
}
