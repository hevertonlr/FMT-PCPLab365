package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.TeacherCreateRequest;
import com.lab365.app.pcp.controller.dto.request.TeacherUpdateRequest;
import com.lab365.app.pcp.controller.dto.response.TeacherReponse;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.service.TeacherService;
import com.lab365.app.pcp.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequestMapping(value = "docentes", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {
    private final TeacherService service;
    private final UserService userService;

    public TeacherController(TeacherService service, UserService userService) {
//        super(service);
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TeacherReponse> create(@Valid @RequestBody TeacherCreateRequest request) {
        String requestedValue = "POST /docentes";
        log.info("{}", requestedValue);
        Teacher entity = request.toEntity();
        userService.save(entity.getUser());

        entity = service.save(entity);
        log.info("{} -> Cadastrado", requestedValue);
        log.debug("{} -> Response Body:\n{}\n", requestedValue, toJSON(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(TeacherReponse.fromEntity(entity));
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherReponse> findById(@PathVariable Long id) {
        log.info("GET /docentes/{} -> Início", id);
        Teacher entity = service.findById(id);
        log.info("GET /docentes/{} -> Encontrado", id);
        log.debug("GET /docentes/{} -> Response Body:\n{}\n", id, toJSON(entity));
        return ResponseEntity.ok(TeacherReponse.fromEntity(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<TeacherReponse> update(@RequestBody TeacherUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /docentes/{} -> Início", id);
        Teacher entity = request.toEntity();
        entity.setId(id);
        entity = service.save(entity);
        log.info("PUT /docentes/{} -> Atualizado", id);
        log.debug("PUT /docentes/{} -> Response Body:\n{}\n", id, toJSON(entity));
        return ResponseEntity.ok(TeacherReponse.fromEntity(entity));
    }
}
