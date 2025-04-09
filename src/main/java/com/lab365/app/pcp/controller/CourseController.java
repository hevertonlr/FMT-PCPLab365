package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.CourseRequest;
import com.lab365.app.pcp.controller.dto.response.CourseResponse;
import com.lab365.app.pcp.controller.dto.response.SubjectResponse;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.service.IGenericService;
import com.lab365.app.pcp.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Tag(name = "Cursos")
@Slf4j
@RestController
@RequestMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController extends GenericController<Course, CourseResponse> {
    private final SubjectService subjectService;
    private final CourseResponse courseResponse;
    private final SubjectResponse subjectResponse;

    public CourseController(IGenericService<Course> service,
            SubjectService subjectService,
            CourseResponse courseResponse,
            SubjectResponse subjectResponse) {
        super(service, courseResponse);
        this.subjectService = subjectService;
        this.courseResponse = courseResponse;
        this.subjectResponse = subjectResponse;
    }

    @Operation(summary = "Criar", description = "Cria um curso")
    @PostMapping
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest request) {
        log.info("POST /cursos");
        Course entity = super.service.save(request.toEntity());
        CourseResponse response = courseResponse.fromEntity(entity);
        log.info("POST /cursos -> Cadastrado");
        log.debug("POST /cursos -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza um curso pelo ID")
    @PutMapping("{id}")
    public ResponseEntity<CourseResponse> update(@Valid @RequestBody CourseRequest request, @PathVariable Long id) {
        log.info("PUT /cursos/{}", id);
        Course entity = request.toEntity();
        entity.setId(id);
        log.info("PUT /cursos/{} -> Atualizado", id);
        Course savedEntity = super.service.save(entity);
        CourseResponse response = courseResponse.fromEntity(savedEntity);
        log.debug("PUT /cursos/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar Matérias", description = "Lista as matérias de um Curso pelo ID")
    @GetMapping(value = "{id}/materias")
    public ResponseEntity<List<SubjectResponse>> list(@PathVariable Long id) {
        log.info("GET /cursos/{}/materias", id);
        List<Subject> entities = subjectService.findAllByCourseId(id);
        log.info("GET /cursos/{}/materias -> Encontrado(s) {} Curso(s)", id, entities.size());
        List<SubjectResponse> response = entities.stream().map(subjectResponse::fromEntity).toList();
        log.debug("GET /cursos/{}/materias -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Lista todos os cursos cadastrados")
    @GetMapping()
    public ResponseEntity<List<CourseResponse>> list() {
        log.info("GET /cursos -> Início");
        List<Course> entities = super.service.findAll();
        log.info("GET /cursos -> Encontrados {} registros", entities.size());
        List<CourseResponse> response = entities.stream().map(courseResponse::fromEntity).toList();
        log.debug("GET /cursos -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(response);
    }

}
