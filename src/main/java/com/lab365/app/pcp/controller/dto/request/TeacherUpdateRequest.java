package com.lab365.app.pcp.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.enums.CivilStateEnum;
import com.lab365.app.pcp.datasource.enums.GenderEnum;

import java.time.LocalDate;

public record TeacherUpdateRequest(
        String name,
        GenderEnum gender,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate birthday,
        String cpf,
        String rg,
        String phone,
        CivilStateEnum civilState,
        String nationality,
        AddressUpdateRequest address,
        UserUpdateRequest user
) {
    public Teacher toEntity() {
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
        entity.setUser(user.toEntity());
        return entity;
    }
}