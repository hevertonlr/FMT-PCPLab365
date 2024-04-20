package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.SubjectRequest;
import com.lab365.app.pcp.controller.dto.response.SubjectResponse;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "materias", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {
    private final SubjectService service;

    @PostMapping
    public ResponseEntity<SubjectResponse> create(@Valid @RequestBody SubjectRequest request) {
        log.info("POST /materias");
        Subject entity = service.save(request.toEntity());
        log.info("POST /materias -> Cadastrado");
        SubjectResponse response = SubjectResponse.fromEntity(entity);
        log.debug("POST /materias -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<SubjectResponse> findById(@PathVariable Long id) {
        log.info("GET /materias -> Início");
        Subject entity = service.findById(id);
        log.info("GET /materias -> Encontrado");
        SubjectResponse response = SubjectResponse.fromEntity(entity);
        log.debug("GET /materias -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<SubjectResponse> update(@Valid @RequestBody SubjectRequest request, @PathVariable Long id) {
        log.info("PUT /materias/{}", id);
        Subject entity = request.toEntity();
        entity.setId(id);
        log.info("PUT /materias/{} -> Atualizado", id);
        SubjectResponse response = SubjectResponse.fromEntity(service.save(entity));
        log.debug("PUT /materias/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /materias/{}", id);
        service.delete(id);
        log.info("DELETE /materias/{} -> Excluído", id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }
}
