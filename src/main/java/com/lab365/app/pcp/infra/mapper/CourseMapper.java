package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.CourseResponseDTO;
import com.lab365.app.pcp.datasource.entity.Course;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface CourseMapper extends IGenericMapper<Course, CourseResponseDTO> {
    @Override
    CourseResponseDTO toDto(Course entity);

    @Override
    Course toEntity(CourseResponseDTO dto);

    @Override
    default Class<Course> getEntityClass() {
        return Course.class;
    }

    @Override
    default Class<CourseResponseDTO> getDestinationClass() {
        return CourseResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget Course entity, CourseResponseDTO dto);
}
