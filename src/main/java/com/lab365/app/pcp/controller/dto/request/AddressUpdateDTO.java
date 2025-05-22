package com.lab365.app.pcp.controller.dto.request;

import lombok.Data;

@Data
public class AddressUpdateDTO {
    private String cep;
    private String city;
    private String state;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String referencePoint;
}
