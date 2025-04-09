package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Address;
import org.hibernate.Hibernate;

public record AddressResponse(Long id, String cep, String city, String state, String street, String number,
                              String complement, String neighborhood, String referencePoint) {

    public static AddressResponse fromEntity(Address entity) {
        if(entity == null || !Hibernate.isInitialized(entity)) return null;
        return new AddressResponse(entity.getId(), entity.getCep(), entity.getCity(), entity.getState(),
                entity.getStreet(), entity.getNumber(), entity.getComplement(), entity.getNeighborhood(),
                entity.getReferencePoint());
    }
}
