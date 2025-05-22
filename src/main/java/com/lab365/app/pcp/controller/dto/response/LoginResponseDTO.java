package com.lab365.app.pcp.controller.dto.response;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private Long expiration;
}
