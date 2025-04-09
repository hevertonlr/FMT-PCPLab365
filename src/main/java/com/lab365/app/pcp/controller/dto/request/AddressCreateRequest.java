package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.Address;

public record AddressCreateRequest(String cep, String city, String state, String street, String number,
                                   String complement, String neighborhood, String referencePoint) {

    public Address toEntity() {
        Address address = new Address();
        address.setCep(cep);
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setNumber(number);
        address.setComplement(complement);
        address.setNeighborhood(neighborhood);
        address.setReferencePoint(referencePoint);
        return address;
    }
}
