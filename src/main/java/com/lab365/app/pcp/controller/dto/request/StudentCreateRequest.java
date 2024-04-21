package com.lab365.app.pcp.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Role;
import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.enums.RolesEnum;
import com.lab365.app.pcp.infra.validation.annotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentCreateRequest(
        @NotBlank String name,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate birthday,
        @NotNull Long classroomid,
        @NotBlank String login,
        @ValidPassword String password
) {
    public Student toEntity() {
        Role role = new Role();
        role.setName(RolesEnum.ALUNO.toString());
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setRole(role);
        Student entity = new Student();
        entity.setName(name);
        entity.setBirthday(birthday);
        entity.setUser(user);
        return entity;
    }
}
