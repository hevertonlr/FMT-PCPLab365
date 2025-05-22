package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.datasource.entity.GenericEntity;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IgnoreAuditableConfig {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifyBy", ignore = true)
    @Mapping(target = "lastModifyAt", ignore = true)
    void IgnoreAuditableFields(GenericEntity<?> source, @MappingTarget GenericEntity<?> target);
}
