package com.lab365.app.pcp.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Role;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.enums.RolesEnum;
import com.lab365.app.pcp.infra.validation.annotation.ValidPassword;
import com.lab365.app.pcp.infra.validation.annotation.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TeacherCreateRequest(
        @NotBlank String name,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate entryDate,
        @NotBlank @ValueOfEnum(enumClass = RolesEnum.class, excludedValues = {"ALUNO", "ADM"}) String profile,
        @NotBlank String login,
        @ValidPassword String password
) {
    public Teacher toEntity() {
        Role role = new Role();
        role.setName(profile);
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setRole(role);
        Teacher entity = new Teacher();
        entity.setName(name);
        entity.setEntryDate(entryDate);
        entity.setUser(user);
        return entity;
    }
}
