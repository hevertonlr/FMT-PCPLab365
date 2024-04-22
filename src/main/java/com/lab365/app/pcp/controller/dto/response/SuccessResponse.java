package com.lab365.app.pcp.controller.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record SuccessResponse(String message) {
    public static ResponseEntity<SuccessResponse> toResponseEntity(String message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("Usuário Criado com Sucesso!"));
    }
}
