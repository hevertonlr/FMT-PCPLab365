package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.StudentCreateRequest;
import com.lab365.app.pcp.controller.dto.request.StudentUpdateRequest;
import com.lab365.app.pcp.controller.dto.response.GradeResponse;
import com.lab365.app.pcp.controller.dto.response.StudentResponse;
import com.lab365.app.pcp.controller.dto.response.StudentTotalScoreResponse;
import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.service.GradeService;
import com.lab365.app.pcp.service.IGenericService;
import com.lab365.app.pcp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.lab365.app.pcp.infra.utils.Util.*;

@Tag(name = "Alunos")
@Slf4j
@RestController
@RequestMapping(value = "alunos", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController extends GenericController<Student, StudentResponse> {

    private final UserService userService;
    private final IGenericService<Classroom> classroomService;
    private final GradeService gradeService;
    private final StudentResponse studentResponse;
    private final GradeResponse gradeResponse;

    public StudentController(IGenericService<Student> service,
            UserService userService,
            IGenericService<Classroom> classroomService,
            GradeService gradeService,
            StudentResponse studentResponse,
            GradeResponse gradeResponse) {
        super(service, studentResponse);
        this.userService = userService;
        this.classroomService = classroomService;
        this.gradeService = gradeService;
        this.studentResponse = studentResponse;
        this.gradeResponse = gradeResponse;
    }

    @Operation(summary = "Criar", description = "Cria um novo aluno")
    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentCreateRequest request) {
        log.info("POST /alunos");
        Student entity = request.toEntity();
        Classroom classroom = classroomService.findById(request.classroomid());
        entity.setClassroom(classroom);
        userService.save(entity.getUser());
        log.info("POST /alunos -> Cadastrado");
        Student savedEntity = super.service.save(entity);
        StudentResponse response = studentResponse.fromEntity(savedEntity);
        log.debug("POST /alunos -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza um aluno pelo ID")
    @PutMapping("{id}")
    public ResponseEntity<StudentResponse> update(@RequestBody StudentUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /alunos/{} -> Início", id);
        Student entity = request.toEntity();
        entity.setId(id);
        Student savedEntity = super.service.save(entity);
        log.info("PUT /alunos/{} -> Atualizado", id);
        StudentResponse response = studentResponse.fromEntity(savedEntity);
        log.debug("PUT /alunos/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Lista todos os alunos cadastrados")
    @GetMapping()
    public ResponseEntity<List<StudentResponse>> list() {
        log.info("GET /alunos -> Início");
        List<Student> entities = super.service.findAll();
        log.info("GET /alunos -> Encontrados {} registros", entities.size());
        List<StudentResponse> response = entities.stream().map(studentResponse::fromEntity).toList();
        log.debug("GET /alunos -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Notas", description = "Busca as notas de um aluno específico pelo ID")
    @GetMapping(value = "{id}/notas")
    public ResponseEntity<?> listGradesBy(@PathVariable Long id) {
        log.info("GET /alunos/{}/notas", id);

        ResponseEntity<?> validation = validateRequest(id);
        if (validation != null)
            return validation;

        List<Grade> entities = gradeService.findAllByStudentId(id);
        log.info("GET /alunos/{}/notas -> Encontrados {} registros", id, entities.size());
        List<GradeResponse> response = entities.stream().map(gradeResponse::fromEntity).toList();
        log.debug("GET /alunos/{}/notas -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Pontuação", description = "Busca a pontuação de um aluno específico pelo ID")
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
        return !getUserIdFromToken().equals(id)
                && !Arrays.asList("ADMINISTRATOR", "PEDAGOGICO").contains(getRoleFromToken())
                        ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
                        : null;
    }
}
