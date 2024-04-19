package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.StudentCreateRequest;
import com.lab365.app.pcp.controller.dto.request.StudentUpdateRequest;
import com.lab365.app.pcp.controller.dto.response.StudentResponse;
import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.service.IGenericService;
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
@RequestMapping(value = "alunos", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {
    private final IGenericService<Student> service;
    private final UserService userService;
    private final IGenericService<Classroom> classroomService;

    @PostMapping
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentCreateRequest request) {
        log.info("POST /alunos");
        Student entity = request.toEntity();
        userService.save(entity.getUser());
        Classroom classroom = classroomService.findById(request.classroomid());
        entity.setClassroom(classroom);

        entity = service.save(entity);
        log.info("POST /alunos -> Cadastrado");
        log.debug("POST /alunos -> Response Body:\n{}\n", toJSON(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentResponse.fromEntity(entity));
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        log.info("GET /alunos/{} -> Início", id);
        Student entity = service.findById(id);
        log.info("GET /alunos/{} -> Encontrado", id);
        log.debug("GET /alunos/{} -> Response Body:\n{}\n", id, toJSON(entity));
        return ResponseEntity.ok(StudentResponse.fromEntity(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentResponse> update(@RequestBody StudentUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /alunos/{} -> Início", id);
        Student entity = request.toEntity();
        entity.setId(id);
        entity = service.save(entity);
        log.info("PUT /alunos/{} -> Atualizado", id);
        log.debug("PUT /alunos/{} -> Response Body:\n{}\n", id, toJSON(entity));
        return ResponseEntity.ok(StudentResponse.fromEntity(entity));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /alunos/{}", id);
        service.delete(id);
        log.info("DELETE /alunos/{} -> Excluído", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<StudentResponse>> list() {
        log.info("GET /alunos -> Início");
        List<Student> entities = service.findAll();
        log.info("GET /alunos -> Encontrados {} registros", entities.size());
        log.debug("GET /alunos -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(StudentResponse.fromEntity(entities));
    }
}
