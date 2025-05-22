package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import com.lab365.app.pcp.infra.validation.annotation.ValidPassword;

@Data
public class UserCreateRequest {
        @NotBlank(message = "Username is required")
        private String username;
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;
        private String image;
        @NotBlank(message = "Password is required")
        @ValidPassword
        private String password;
}