package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.GradeResponseDTO;
import com.lab365.app.pcp.datasource.entity.Grade;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface GradeMapper extends IGenericMapper<Grade, GradeResponseDTO> {
    @Override
    GradeResponseDTO toDto(Grade entity);

    @Override
    Grade toEntity(GradeResponseDTO dto);

    @Override
    default Class<Grade> getEntityClass() {
        return Grade.class;
    }

    @Override
    default Class<GradeResponseDTO> getDestinationClass() {
        return GradeResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget Grade entity, GradeResponseDTO dto);
}
