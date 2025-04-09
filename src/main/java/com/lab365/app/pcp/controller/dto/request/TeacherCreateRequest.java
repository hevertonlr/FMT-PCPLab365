package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Role;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.enums.CivilStateEnum;
import com.lab365.app.pcp.datasource.enums.GenderEnum;
import com.lab365.app.pcp.datasource.enums.RolesEnum;

public record TeacherCreateRequest(

        // @NotBlank @ValueOfEnum(enumClass = RolesEnum.class, excludedValues =
        // {"ALUNO", "ADMINISTRATOR"}) String profile,
        // @NotBlank String login,
        // @ValidPassword String password,

        @NotBlank String name,
        GenderEnum gender,
        @JsonFormat(pattern = "dd/MM/yyyy") @JsonSerialize(using = LocalDateSerializer.class) @JsonDeserialize(using = LocalDateDeserializer.class) LocalDate birthday,
        String cpf,
        String rg,
        String phone,
        CivilStateEnum civilState,
        String nationality,
        AddressCreateRequest address,
        @NotBlank UserCreateRequest user,
        Set<Long> subjects) {
    public Teacher toEntity() {
        Role role = new Role();
        role.setName(RolesEnum.TEACHER.toString());
        User user = this.user.toEntity();
        user.setRole(role);
        Teacher entity = new Teacher();
        entity.setName(name);
        entity.setGender(gender);
        entity.setBirthday(birthday);
        entity.setCpf(cpf);
        entity.setRg(rg);
        entity.setPhone(phone);
        entity.setCivilState(civilState);
        entity.setNationality(nationality);
        entity.setAddress(address.toEntity());
        entity.setUser(user);
        entity.setSubjects(subjects.stream().map(id -> {
            Subject subject = new Subject();
            subject.setId(id);
            return subject;
        }).collect(Collectors.toSet()));
        return entity;
    }
}
