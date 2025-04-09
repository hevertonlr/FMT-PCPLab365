package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.enums.CivilStateEnum;
import com.lab365.app.pcp.datasource.enums.GenderEnum;

import java.util.Optional;


public record TeacherResponse(Long id, String name, GenderEnum gender, String birthday, String cpf,
                              String rg, String phone, Optional<AddressResponse> address, CivilStateEnum civilState,
                              String nationality, Optional<UserResponse> user) implements IGenericResponseDTO<Teacher,TeacherResponse>{

    @Override
    public TeacherResponse fromEntity(Teacher entity) {
        return new TeacherResponse(entity.getId(), entity.getName(), entity.getGender(), entity.getBirthday().toString(),
                entity.getCpf(), entity.getRg(), entity.getPhone(), Optional.ofNullable(entity.getAddress()).map(AddressResponse::fromEntity),
                entity.getCivilState(), entity.getNationality(),Optional.ofNullable(entity.getUser()).map(UserResponse::fromEntity));
    }
}