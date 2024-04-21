package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.CourseRequest;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.service.IGenericService;
import com.lab365.app.pcp.service.SubjectService;
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
@RequestMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController extends GenericController<Course> {
    private final SubjectService subjectService;

    public CourseController(IGenericService<Course> service, SubjectService subjectService) {
        super(service);
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody CourseRequest request) {
        log.info("POST /cursos");
        Course entity = super.service.save(request.toEntity());
        log.info("POST /cursos -> Cadastrado");
        log.debug("POST /cursos -> Response Body:\n{}\n", toJSON(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping("{id}")
    public ResponseEntity<Course> update(@Valid @RequestBody CourseRequest request, @PathVariable Long id) {
        log.info("PUT /cursos/{}", id);
        Course entity = request.toEntity();
        entity.setId(id);
        log.info("PUT /cursos/{} -> Atualizado", id);
        Course response = super.service.save(entity);
        log.debug("PUT /cursos/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "{id}/materias")
    public ResponseEntity<List<Subject>> list(@PathVariable Long id) {
        log.info("GET /cursos/{}/materias", id);
        List<Subject> entities = subjectService.findAllByCourseId(id);
        log.info("GET /cursos/{}/materias -> Encontrado(s) {} Curso(s)", id, entities.size());
        log.debug("GET /cursos/{}/materias -> Response Body:\n{}\n", id, toJSON(entities));
        return ResponseEntity.ok(entities);
    }

}
