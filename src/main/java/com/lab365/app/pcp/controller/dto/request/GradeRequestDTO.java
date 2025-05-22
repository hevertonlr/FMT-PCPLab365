package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Data
public class GradeRequestDTO {
    @NotNull(message = "Grade value is required")
    @DecimalMin(value = "0.0", message = "Grade value must be at least 0")
    @DecimalMax(value = "10.0", message = "Grade value must be at most 10")

    private BigDecimal value;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    @NotNull(message = "Student ID is required")
    private Long studentid;
    @NotNull(message = "Teacher ID is required")
    private Long teacherid;
    @NotNull(message = "Subject ID is required")
    private Long subjectid;

}
