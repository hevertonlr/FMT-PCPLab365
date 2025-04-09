package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.datasource.enums.GenderEnum;

import java.util.Optional;

public record StudentResponse(Long id, String name, GenderEnum gender, String cpf, String rg, String phone, String birthday, String placeofbirth,
                              Optional<AddressResponse> address, Optional<UserResponse> user) implements IGenericResponseDTO<Student,StudentResponse> {

    @Override
    public StudentResponse fromEntity(Student entity) {
        return new StudentResponse(entity.getId(), entity.getName(), entity.getGender(), entity.getCpf(), entity.getRg(),
                entity.getPhone(), entity.getBirthday().toString(), entity.getPlaceofbirth(), Optional.ofNullable(entity.getAddress()).map(AddressResponse::fromEntity),
                Optional.ofNullable(entity.getUser()).map(UserResponse::fromEntity));
    }
}