package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.enums.GenderEnum;

@Data
public abstract class PersonRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Gender is required")
    private GenderEnum gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;

    @NotBlank(message = "CPF is required")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "CPF must be in format XXX.XXX.XXX-XX")
    private String cpf;

    @NotBlank(message = "RG is required")
    private String rg;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}\\-\\d{4}$", message = "Phone must be in format (XX) XXXXX-XXXX")
    private String phone;

    @NotNull(message = "Address is required")
    private Long addressId;
}
