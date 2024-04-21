package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.SubjectRequest;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.service.CourseService;
import com.lab365.app.pcp.service.SubjectService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequestMapping(value = "materias", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController extends GenericController<Subject> {
    private final CourseService courseService;

    public SubjectController(SubjectService service, CourseService courseService) {
        super(service);
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Subject> create(@Valid @RequestBody SubjectRequest request) {
        log.info("POST /materias");
        Subject entity = request.toEntity();
        Course course = courseService.findById(request.courseid());
        entity.setCourse(course);
        Subject response = super.service.save(entity);
        log.info("POST /materias -> Cadastrada");
        log.debug("POST /materias -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Subject> update(@Valid @RequestBody SubjectRequest request, @PathVariable Long id) {
        log.info("PUT /materias/{}", id);
        Subject entity = request.toEntity();
        entity.setId(id);
        log.info("PUT /materias/{} -> Atualizada", id);
        Subject response = super.service.save(entity);
        log.debug("PUT /materias/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }
}
