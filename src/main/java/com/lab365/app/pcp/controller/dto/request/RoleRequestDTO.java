package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequestDTO {
        @NotBlank(message = "Role name is required")
        private String name;
}
