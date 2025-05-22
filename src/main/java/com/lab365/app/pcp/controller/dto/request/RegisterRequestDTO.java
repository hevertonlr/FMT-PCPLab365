package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import com.lab365.app.pcp.infra.validation.annotation.ValidPassword;

@Data
public class RegisterRequestDTO {
    @NotNull
    private String username;
    @ValidPassword
    private String password;
    @NotNull
    @Email
    private String email;
    private String image;
}
