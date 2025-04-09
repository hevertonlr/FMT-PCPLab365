package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.lab365.app.pcp.controller.dto.request.SubjectRequest;
import com.lab365.app.pcp.controller.dto.response.SubjectResponse;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.service.CourseService;
import com.lab365.app.pcp.service.SubjectService;

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

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Tag(name = "Matérias")
@Slf4j
@RestController
@RequestMapping(value = "materias", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController extends GenericController<Subject, SubjectResponse> {
    private final CourseService courseService;
    private final SubjectResponse subjectResponse;

    public SubjectController(SubjectService service, CourseService courseService, SubjectResponse subjectResponse) {
        super(service, subjectResponse);
        this.courseService = courseService;
        this.subjectResponse = subjectResponse;
    }

    @Operation(summary = "Criar", description = "Cria uma nova matéria")
    @PostMapping
    public ResponseEntity<SubjectResponse> create(@Valid @RequestBody SubjectRequest request) {
        log.info("POST /materias");
        Subject entity = request.toEntity();
        Course course = courseService.findById(request.courseid());
        entity.setCourse(course);
        Subject savedEntity = super.service.save(entity);
        log.info("POST /materias -> Cadastrada");
        SubjectResponse response = subjectResponse.fromEntity(savedEntity);
        log.debug("POST /materias -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Listar todas as turmas")
    @PutMapping("{id}")
    public ResponseEntity<SubjectResponse> update(@Valid @RequestBody SubjectRequest request, @PathVariable Long id) {
        log.info("PUT /materias/{}", id);
        Subject entity = request.toEntity();
        entity.setId(id);
        log.info("PUT /materias/{} -> Atualizada", id);
        Subject savedEntity = super.service.save(entity);
        SubjectResponse response = subjectResponse.fromEntity(savedEntity);
        log.debug("PUT /materias/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Listar todas as materias")
    @GetMapping()
    public ResponseEntity<List<SubjectResponse>> list() {
        log.info("GET /turmas -> Início");
        List<Subject> entities = super.service.findAll();
        log.info("GET /turmas -> Encontrada(s) {} Turmas", entities.size());
        List<SubjectResponse> response = entities.stream().map(subjectResponse::fromEntity).toList();
        log.debug("GET /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.ok(response);
    }
}
