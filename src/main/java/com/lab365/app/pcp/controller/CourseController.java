package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.CourseRequest;
import com.lab365.app.pcp.controller.dto.response.CourseResponse;
import com.lab365.app.pcp.controller.dto.response.SubjectResponse;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.service.CourseService;
import com.lab365.app.pcp.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {
    private final CourseService service;
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest request) {
        log.info("POST /cursos");
        Course entity = service.save(request.toEntity());
        log.info("POST /cursos -> Cadastrado");
        CourseResponse response = CourseResponse.fromEntity(entity);
        log.debug("POST /cursos -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseResponse> findById(@PathVariable Long id) {
        log.info("GET /cursos/{} -> Início", id);
        Course entity = service.findById(id);
        log.info("GET /cursos/{} -> Encontrado", id);
        CourseResponse response = CourseResponse.fromEntity(entity);
        log.debug("GET /cursos/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<CourseResponse> update(@Valid @RequestBody CourseRequest request, @PathVariable Long id) {
        log.info("PUT /cursos/{}", id);
        Course entity = service.save(request.toEntity());
        log.info("PUT /cursos/{} -> Atualizado", id);
        CourseResponse response = CourseResponse.fromEntity(entity);
        log.debug("PUT /cursos/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /cursos/{}", id);
        service.delete(id);
        log.info("DELETE /cursos/{} -> Excluído", id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

    @GetMapping(value = "{id}/materias")
    public ResponseEntity<List<SubjectResponse>> list(@PathVariable Long id) {
        log.info("GET /cursos/{}/materias -> Início", id);
        List<Subject> entities = subjectService.findAllByCourseId(id);
        log.info("GET /cursos/{}/materias -> Encontrados {} registros", id, entities.size());
        List<SubjectResponse> response = SubjectResponse.fromEntity(entities);
        log.debug("GET /cursos/{}/materias -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

//    @GetMapping()
//    public ResponseEntity<List<CourseResponse>> list() {
//        log.info("GET /cursos -> Início");
//        List<Course> entities = service.findAll();
//        log.info("GET /cursos -> Encontrados {} registros", entities.size());
//        log.debug("GET /cursos -> Response Body:\n{}\n", toJSON(entities));
//        return ResponseEntity.ok(CourseResponse.fromEntity(entities));
//    }
}
