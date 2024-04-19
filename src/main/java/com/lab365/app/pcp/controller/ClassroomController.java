package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.ClassroomRequest;
import com.lab365.app.pcp.controller.dto.response.ClassroomResponse;
import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.service.IGenericService;
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
@RequestMapping(value = "turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClassroomController {
    private final IGenericService<Classroom> service;

    @PostMapping
    public ResponseEntity<ClassroomResponse> create(@Valid @RequestBody ClassroomRequest request) {
        log.info("POST /turmas");
        Classroom entity = service.save(request.toEntity());
        log.info("POST /turmas -> Cadastrado");
        ClassroomResponse response = ClassroomResponse.fromEntity(entity);
        log.debug("POST /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClassroomResponse> findById(@PathVariable Long id) {
        log.info("GET /turmas/{} -> Início", id);
        Classroom entity = service.findById(id);
        log.info("GET /turmas/{} -> Encontrado", id);
        ClassroomResponse response = ClassroomResponse.fromEntity(entity);
        log.debug("GET /turmas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClassroomResponse> update(@Valid @RequestBody ClassroomRequest request, @PathVariable Long id) {
        log.info("PUT /turmas/{}", id);
        Classroom entity = service.save(request.toEntity());
        log.info("PUT /turmas/{} -> Atualizado", id);
        ClassroomResponse response = ClassroomResponse.fromEntity(entity);
        log.debug("PUT /turmas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /turmas/{}", id);
        service.delete(id);
        log.info("DELETE /turmas/{} -> Excluído", id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

    @GetMapping()
    public ResponseEntity<List<ClassroomResponse>> list() {
        log.info("GET /turmas -> Início");
        List<Classroom> entities = service.findAll();
        log.info("GET /turmas -> Encontrados {} registros", entities.size());
        List<ClassroomResponse> response = ClassroomResponse.fromEntity(entities);
        log.debug("GET /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.ok(ClassroomResponse.fromEntity(entities));
    }
}
