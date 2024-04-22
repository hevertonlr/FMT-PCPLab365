package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.StudentCreateRequest;
import com.lab365.app.pcp.controller.dto.request.StudentUpdateRequest;
import com.lab365.app.pcp.controller.dto.response.StudentTotalScoreResponse;
import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.service.GradeService;
import com.lab365.app.pcp.service.IGenericService;
import com.lab365.app.pcp.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.lab365.app.pcp.infra.utils.Util.*;

@Slf4j
@RestController
@RequestMapping(value = "alunos", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController extends GenericController<Student> {

    private final UserService userService;
    private final IGenericService<Classroom> classroomService;
    private final GradeService gradeService;

    public StudentController(IGenericService<Student> service,
                             UserService userService,
                             IGenericService<Classroom> classroomService,
                             GradeService gradeService) {
        super(service);
        this.userService = userService;
        this.classroomService = classroomService;
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody StudentCreateRequest request) {
        log.info("POST /alunos");
        Student entity = request.toEntity();
        Classroom classroom = classroomService.findById(request.classroomid());
        entity.setClassroom(classroom);
        userService.save(entity.getUser());
        log.info("POST /alunos -> Cadastrado");
        Student response = super.service.save(entity);
        log.debug("POST /alunos -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> update(@RequestBody StudentUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /alunos/{} -> Início", id);
        Student entity = request.toEntity();
        entity.setId(id);
        Student response = super.service.save(entity);
        log.info("PUT /alunos/{} -> Atualizado", id);
        log.debug("PUT /alunos/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> list() {
        log.info("GET /alunos -> Início");
        List<Student> entities = super.service.findAll();
        log.info("GET /alunos -> Encontrados {} registros", entities.size());
        log.debug("GET /alunos -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(entities);
    }

    @GetMapping(value = "{id}/notas")
    public ResponseEntity<?> listGradesBy(@PathVariable Long id) {
        log.info("GET /alunos/{}/notas", id);

        ResponseEntity<?> validation = validateRequest(id);
        if (validation != null)
            return validation;

        List<Grade> entities = gradeService.findAllByStudentId(id);

        log.info("GET /alunos/{}/notas -> Encontrados {} registros", id, entities.size());
        log.debug("GET /alunos/{}/notas -> Response Body:\n{}\n", id, toJSON(entities));
        return ResponseEntity.ok(entities);
    }

    @GetMapping(value = "{id}/pontuacao")
    public ResponseEntity<?> getScore(@PathVariable Long id) {
        log.info("GET /alunos/{}/pontuacao", id);

        ResponseEntity<?> validation = validateRequest(id);
        if (validation != null)
            return validation;

        Object response = StudentTotalScoreResponse.fromMap(gradeService.getScore(id));
        log.debug("GET /alunos/{}/pontuacao -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }


    private ResponseEntity<?> validateRequest(Long id) {
        return !getUserIdFromToken().equals(id) && !Arrays.asList("ADM", "PEDAGOGICO").contains(getRoleFromToken())
                ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
                : null;
    }
}
