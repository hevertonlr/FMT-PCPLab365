package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.TeacherCreateRequest;
import com.lab365.app.pcp.controller.dto.request.TeacherUpdateRequest;
import com.lab365.app.pcp.controller.dto.response.TeacherResponse;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.service.TeacherService;
import com.lab365.app.pcp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "docentes", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {
    private final TeacherService service;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<TeacherResponse> create(@Valid @RequestBody TeacherCreateRequest request) {
        log.info("POST /docentes");
        Teacher entity = request.toEntity();
        userService.save(entity.getUser());

        entity = service.save(entity);
        log.info("POST /docentes -> Cadastrado");
        log.debug("POST /docentes -> Response Body:\n{}\n", toJSON(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(TeacherResponse.fromEntity(entity));
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherResponse> findById(@PathVariable Long id) {
        log.info("GET /docentes/{} -> Início", id);
        Teacher entity = service.findById(id);
        log.info("GET /docentes/{} -> Encontrado", id);
        log.debug("GET /docentes/{} -> Response Body:\n{}\n", id, toJSON(entity));
        return ResponseEntity.ok(TeacherResponse.fromEntity(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<TeacherResponse> update(@RequestBody TeacherUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /docentes/{} -> Início", id);
        Teacher entity = request.toEntity();
        entity.setId(id);
        entity = service.save(entity);
        log.info("PUT /docentes/{} -> Atualizado", id);
        log.debug("PUT /docentes/{} -> Response Body:\n{}\n", id, toJSON(entity));
        return ResponseEntity.ok(TeacherResponse.fromEntity(entity));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADM')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /docentes/{}", id);
        service.delete(id);
        log.info("DELETE /docentes/{} -> Excluído", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<TeacherResponse>> list() {
        log.info("GET /docentes -> Início");
        List<Teacher> entities = service.findAll();
        log.info("GET /docentes -> Encontrados {} registros", entities.size());
        log.debug("GET /docentes -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(TeacherResponse.fromEntity(entities));
    }
}
