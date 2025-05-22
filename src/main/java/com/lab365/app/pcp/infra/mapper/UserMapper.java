package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.UserResponseDTO;
import com.lab365.app.pcp.datasource.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface UserMapper extends IGenericMapper<User, UserResponseDTO> {
    @Override
    UserResponseDTO toDto(User source);

    @Override
    User toEntity(UserResponseDTO destination);

    @Override
    default Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    default Class<UserResponseDTO> getDestinationClass() {
        return UserResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget User entity, UserResponseDTO dto);
}
