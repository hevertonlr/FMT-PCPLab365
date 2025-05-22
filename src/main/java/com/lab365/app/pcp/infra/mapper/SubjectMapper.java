package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.SubjectResponseDTO;
import com.lab365.app.pcp.datasource.entity.Subject;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface SubjectMapper extends IGenericMapper<Subject, SubjectResponseDTO> {
    @Override
    SubjectResponseDTO toDto(Subject entity);

    @Override
    Subject toEntity(SubjectResponseDTO dto);

    @Override
    default Class<Subject> getEntityClass() {
        return Subject.class;
    }

    @Override
    default Class<SubjectResponseDTO> getDestinationClass() {
        return SubjectResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget Subject entity, SubjectResponseDTO dto);
}
