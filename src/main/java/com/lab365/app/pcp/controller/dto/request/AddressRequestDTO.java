package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressRequestDTO {
        @NotBlank(message = "CEP is required")
        @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP must be in format XXXXX-XXX")
        private String cep;
        @NotBlank(message = "City is required")
        private String city;
        @NotBlank(message = "State is required")
        private String state;
        @NotBlank(message = "Street is required")
        private String street;
        @NotBlank(message = "Number is required")
        private String number;
        private String complement;
        @NotBlank(message = "Neighborhood is required")
        private String neighborhood;
        private String referencePoint;

}
