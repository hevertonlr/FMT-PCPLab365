package com.lab365.app.pcp.infra.mapper;

import com.lab365.app.pcp.controller.dto.response.AddressResponseDTO;
import com.lab365.app.pcp.datasource.entity.Address;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = IgnoreAuditableConfig.class, componentModel = "spring")
public interface AddressMapper extends IGenericMapper<Address, AddressResponseDTO> {

    @Override
    AddressResponseDTO toDto(Address entity);

    @Override
    Address toEntity(AddressResponseDTO dto);

    @Override
    default Class<Address> getEntityClass() {
        return Address.class;
    }

    @Override
    default Class<AddressResponseDTO> getDestinationClass() {
        return AddressResponseDTO.class;
    }

    @Override
    void updateEntity(@MappingTarget Address entity, AddressResponseDTO dto);
}
