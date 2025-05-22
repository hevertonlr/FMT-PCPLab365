package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.ClassroomResponseDTO;
import com.lab365.app.pcp.datasource.entity.Classroom;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface ClassroomMapper extends IGenericMapper<Classroom, ClassroomResponseDTO> {
    @Override
    ClassroomResponseDTO toDto(Classroom entity);

    @Override
    Classroom toEntity(ClassroomResponseDTO dto);

    @Override
    default Class<Classroom> getEntityClass() {
        return Classroom.class;
    }

    @Override
    default Class<ClassroomResponseDTO> getDestinationClass() {
        return ClassroomResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget Classroom entity, ClassroomResponseDTO dto);
}
