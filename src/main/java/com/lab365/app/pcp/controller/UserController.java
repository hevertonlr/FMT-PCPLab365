package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.lab365.app.pcp.controller.dto.request.RegisterRequest;
import com.lab365.app.pcp.controller.dto.response.SuccessResponse;
import com.lab365.app.pcp.controller.dto.response.UserResponse;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.service.UserService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Tag(name = "Usuários")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService service;

    @Operation(summary = "Cadastrar", description = "Cadastrar um usuário")
    @PostMapping("cadastro")
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.info("POST /usuarios/cadastro -> username: {}", request.username());
        User user = request.toEntity();
        service.save(user);
        log.info("POST /usuarios/cadastro -> Cadastrado");
        log.debug("POST /usuarios/cadastro -> Response Body:\n{}\n", toJSON(user));
        return SuccessResponse.toResponseEntity("Usuário Criado com Sucesso!");
    }

    @Operation(summary = "Buscar", description = "Busca um usuário específica pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        log.info("GET /usuarios/{} -> Início", id);
        User entity = service.findById(id);
        log.info("GET /usuarios/{} -> Encontrado(a)", id);
        UserResponse response = UserResponse.fromEntity(entity);
        log.debug("GET /usuarios/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }
}
