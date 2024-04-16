package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.LoginRequest;
import com.lab365.app.pcp.controller.dto.request.RegisterRequest;
import com.lab365.app.pcp.controller.dto.response.LoginResponse;
import com.lab365.app.pcp.controller.dto.response.SuccessResponse;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.service.TokenService;
import com.lab365.app.pcp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService service;
    private final TokenService tokenService;

    /**
     * TODO: VERIFICAR PERMISSÃO DE CADASTRO
     */
    @PostMapping("cadastro")
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.info("POST /cadastro -> username: {}", request.username());
        User user = request.toEntity();
        service.save(user);
        log.info("POST /cadastro -> SUCCESS");
        log.debug("POST /cadastro -> CREATED \n{}\n", toJSON(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("Usuário Criado com Sucesso!"));
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        log.info("POST /login -> username: {}", request.username());
        LoginResponse response = tokenService.getToken(request);
        log.debug("POST /login -> SIGNED \n{}\n", toJSON(response));
        return ResponseEntity.ok(response);
    }
}
