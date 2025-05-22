package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    private String user;
    @NotBlank
    private String password;
}
