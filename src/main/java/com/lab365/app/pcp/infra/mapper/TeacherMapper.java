package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.TeacherResponseDTO;
import com.lab365.app.pcp.datasource.entity.Teacher;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface TeacherMapper extends IGenericMapper<Teacher, TeacherResponseDTO> {
    @Override
    TeacherResponseDTO toDto(Teacher entity);

    @Override
    Teacher toEntity(TeacherResponseDTO dto);

    @Override
    default Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    @Override
    default Class<TeacherResponseDTO> getDestinationClass() {
        return TeacherResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget Teacher entity, TeacherResponseDTO dto);
}
