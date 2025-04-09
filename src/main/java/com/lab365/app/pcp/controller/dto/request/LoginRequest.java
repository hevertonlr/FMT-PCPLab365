package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String user, @NotBlank String password) {
}
