package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.lab365.app.pcp.controller.dto.request.RegisterRequestDTO;
import com.lab365.app.pcp.controller.dto.response.SuccessResponseDTO;
import com.lab365.app.pcp.controller.dto.response.UserResponseDTO;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.infra.mapper.UserMapper;
import com.lab365.app.pcp.service.interfaces.IUserService;

import org.springframework.http.HttpStatus;
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
    private final IUserService service;
    private final UserMapper modelMapper;

    @Operation(summary = "Cadastrar", description = "Cadastrar um usuário")
    @PostMapping("cadastro")
    public ResponseEntity<SuccessResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        log.info("POST /usuarios/cadastro -> username: {}", request.getUsername());
        User user = modelMapper.map(request, User.class);
        service.save(user);
        log.info("POST /usuarios/cadastro -> Cadastrado");
        log.debug("POST /usuarios/cadastro -> Response Body:\n{}\n", toJSON(user));
        SuccessResponseDTO response = new SuccessResponseDTO();
        response.setMessage("Usuário Criado com Sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Buscar", description = "Busca um usuário específica pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        log.info("GET /usuarios/{} -> Início", id);
        User entity = service.findById(id);
        log.info("GET /usuarios/{} -> Encontrado(a)", id);
        UserResponseDTO response = modelMapper.map(entity, UserResponseDTO.class);
        log.debug("GET /usuarios/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }
}
