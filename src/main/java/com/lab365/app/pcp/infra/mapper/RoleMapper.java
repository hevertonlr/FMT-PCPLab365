package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.RoleResponseDTO;
import com.lab365.app.pcp.datasource.entity.Role;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface RoleMapper extends IGenericMapper<Role, RoleResponseDTO> {
    @Override
    RoleResponseDTO toDto(Role entity);

    @Override
    Role toEntity(RoleResponseDTO dto);

    @Override
    default Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    default Class<RoleResponseDTO> getDestinationClass() {
        return RoleResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget Role entity, RoleResponseDTO dto);
}
