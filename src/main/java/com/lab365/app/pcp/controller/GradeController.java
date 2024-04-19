package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.GradeRequest;
import com.lab365.app.pcp.controller.dto.response.GradeResponse;
import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.service.IGenericService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "notas", produces = MediaType.APPLICATION_JSON_VALUE)
public class GradeController {
    private final IGenericService<Grade> service;
    private final IGenericService<Student> studentService;
    private final IGenericService<Teacher> teacherService;
    private final IGenericService<Subject> subjectService;


    @PostMapping
    public ResponseEntity<GradeResponse> create(@Valid @RequestBody GradeRequest request) {
        log.info("POST /notas");
        Grade entity = request.toEntity();
        Student student = studentService.findById(request.studentid());
        entity.setStudent(student);
        Teacher teacher = teacherService.findById(request.teacherid());
        entity.setTeacher(teacher);
        Subject subject = subjectService.findById(request.subjectid());
        entity.setSubject(subject);
        log.info("POST /notas -> Cadastrado");
        GradeResponse response = GradeResponse.fromEntity(service.save(entity));
        log.debug("POST /notas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GradeResponse> findById(@PathVariable Long id) {
        log.info("GET /notas/{}", id);
        Grade entity = service.findById(id);
        log.info("GET /notas/{} -> Encontrado", id);
        GradeResponse response = GradeResponse.fromEntity(entity);
        log.debug("GET /notas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<GradeResponse> update(@Valid @RequestBody GradeRequest request, @PathVariable Long id) {
        log.info("PUT /notas/{}", id);
        Grade entity = service.save(request.toEntity());
        log.info("PUT /notas/{} -> Atualizado", id);
        GradeResponse response = GradeResponse.fromEntity(entity);
        log.debug("PUT /notas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /notas/{}", id);
        service.delete(id);
        log.info("DELETE /notas/{} -> Excluído", id);
        return ResponseEntity.noContent().build();
    }
}
