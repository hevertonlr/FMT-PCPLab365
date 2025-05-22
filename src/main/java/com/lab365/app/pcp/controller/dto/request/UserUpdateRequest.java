package com.lab365.app.pcp.controller.dto.request;

import lombok.Data;

import com.lab365.app.pcp.infra.validation.annotation.ValidPassword;

@Data
public class UserUpdateRequest {
        private String name;
        private String email;
        private String image;
        @ValidPassword
        private String password;
        private Long roleId;
}
