package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.StudentResponseDTO;
import com.lab365.app.pcp.datasource.entity.Student;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface StudentMapper extends IGenericMapper<Student, StudentResponseDTO> {
    @Override
    StudentResponseDTO toDto(Student source);

    @Override
    Student toEntity(StudentResponseDTO destination);

    @Override
    default Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    default Class<StudentResponseDTO> getDestinationClass() {
        return StudentResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget Student entity, StudentResponseDTO dto);
}
