package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.lab365.app.pcp.controller.dto.request.LoginRequest;
import com.lab365.app.pcp.controller.dto.response.LoginResponseDTO;
import com.lab365.app.pcp.service.interfaces.ITokenService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Tag(name = "Autenticação")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final ITokenService tokenService;

    @Operation(summary = "Login", description = "Realiza o login por usuário/email e senha")
    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequest request) {
        log.info("POST /login -> username or email: {}", request.getUser());
        LoginResponseDTO response = tokenService.getToken(request);
        log.debug("POST /login -> SIGNED \n{}\n", toJSON(response));
        return ResponseEntity.ok(response);
    }
}
