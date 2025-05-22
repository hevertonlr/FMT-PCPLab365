package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import com.lab365.app.pcp.controller.dto.request.StudentRequestDTO;
import com.lab365.app.pcp.controller.dto.request.StudentUpdateDTO;
import com.lab365.app.pcp.controller.dto.response.GradeResponseDTO;
import com.lab365.app.pcp.controller.dto.response.StudentResponseDTO;
import com.lab365.app.pcp.controller.dto.response.StudentTotalScoreResponseDTO;
import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.infra.mapper.StudentMapper;
import com.lab365.app.pcp.service.interfaces.IClassroomService;
import com.lab365.app.pcp.service.interfaces.IGradeService;
import com.lab365.app.pcp.service.interfaces.IStudentService;
import com.lab365.app.pcp.service.interfaces.IUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lab365.app.pcp.infra.utils.Util.getRoleFromToken;
import static com.lab365.app.pcp.infra.utils.Util.getUserIdFromToken;
import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Tag(name = "Alunos")
@Slf4j
@RestController
@RequestMapping(value = "alunos", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController extends GenericController<Student, StudentResponseDTO> {

    private final IUserService userService;
    private final IClassroomService classroomService;
    private final IGradeService gradeService;
    private final IStudentService service;

    public StudentController(IStudentService service,
            IUserService userService,
            IClassroomService classroomService,
            IGradeService gradeService,
            StudentResponseDTO studentResponse,
            GradeResponseDTO gradeResponse,
            StudentMapper mapper) {
        super(service, studentResponse, mapper);
        this.userService = userService;
        this.classroomService = classroomService;
        this.gradeService = gradeService;

        this.service = service;
    }

    @Operation(summary = "Criar", description = "Cria um novo aluno")
    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentRequestDTO request) {
        log.info("POST /alunos");
        Student entity = this.mapTo(request, Student.class);
        Classroom classroom = classroomService.findById(request.getClassroomid());
        entity.setClassroom(classroom);
        userService.save(entity.getUser());
        log.info("POST /alunos -> Cadastrado");
        Student savedEntity = super.service.save(entity);
        StudentResponseDTO response = this.mapTo(savedEntity, StudentResponseDTO.class);
        log.debug("POST /alunos -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza um aluno pelo ID")
    @PutMapping("{id}")
    public ResponseEntity<StudentResponseDTO> update(@RequestBody StudentUpdateDTO request, @PathVariable Long id) {
        log.info("PUT /alunos/{} -> Início", id);
        Student entity = this.mapTo(request, Student.class);
        entity.setId(id);
        Student savedEntity = super.service.save(entity);
        log.info("PUT /alunos/{} -> Atualizado", id);
        StudentResponseDTO response = this.mapTo(savedEntity, StudentResponseDTO.class);
        log.debug("PUT /alunos/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Lista todos os alunos cadastrados")
    @GetMapping()
    public ResponseEntity<List<StudentResponseDTO>> list() {
        log.info("GET /alunos -> Início");
        List<Student> entities = super.service.findAll();
        log.info("GET /alunos -> Encontrados {} registros", entities.size());
        List<StudentResponseDTO> response = entities.stream()
                .map(entity -> (StudentResponseDTO) this.mapTo(entity, StudentResponseDTO.class)).toList();
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
        List<GradeResponseDTO> response = this.mapToList(entities, GradeResponseDTO.class);
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

        Object response = StudentTotalScoreResponseDTO.fromMap(gradeService.getScore(id));
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
