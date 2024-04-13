package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.RegisterRequest;
import com.lab365.app.pcp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("cadastro")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest){
        service.save(registerRequest.toEntity());
        return new ResponseEntity<>("Usuário Criado com Sucesso!", HttpStatus.CREATED);
    }
}
