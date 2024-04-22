package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.TeacherCreateRequest;
import com.lab365.app.pcp.controller.dto.request.TeacherUpdateRequest;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.service.IGenericService;
import com.lab365.app.pcp.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequestMapping(value = "docentes", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController extends GenericController<Teacher> {

    private final UserService userService;

    public TeacherController(IGenericService<Teacher> service, UserService userService) {
        super(service);
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@Valid @RequestBody TeacherCreateRequest request) {
        log.info("POST /docentes");
        Teacher entity = request.toEntity();
        userService.save(entity.getUser());
        Teacher response = super.service.save(entity);
        log.info("POST /docentes -> Cadastrado");
        log.debug("POST /docentes -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Teacher> update(@RequestBody TeacherUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /docentes/{} -> Início", id);
        Teacher entity = request.toEntity();
        entity.setId(id);
        Teacher response = super.service.save(entity);
        log.info("PUT /docentes/{} -> Atualizado", id);
        log.debug("PUT /docentes/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<Teacher>> list() {
        log.info("GET /docentes -> Início");
        List<Teacher> entities = service.findAll();
        log.info("GET /docentes -> Encontrado(s) {} Docente(s)", entities.size());
        log.debug("GET /docentes -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(entities);
    }
}
