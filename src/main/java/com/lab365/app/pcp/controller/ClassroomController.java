package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.ClassroomCreateRequest;
import com.lab365.app.pcp.controller.dto.request.ClassroomUpdateRequest;
import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.enums.RolesEnum;
import com.lab365.app.pcp.infra.exception.InvalidException;
import com.lab365.app.pcp.service.IGenericService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequestMapping(value = "turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClassroomController extends GenericController<Classroom> {
    private final IGenericService<Teacher> teacherService;
    private final IGenericService<Course> courseService;

    public ClassroomController(IGenericService<Classroom> service,
                               IGenericService<Teacher> teacherService,
                               IGenericService<Course> courseService) {
        super(service);
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Classroom> create(@Valid @RequestBody ClassroomCreateRequest request) {
        log.info("POST /turmas");
        Classroom entity = request.toEntity();
        Teacher teacher = teacherService.findById(request.teacherid());
        if (!Objects.equals(teacher.getUser().getRole().getName(), RolesEnum.PROFESSOR.toString()))
            throw new InvalidException("Somente um PROFESSOR pode ser atribuído a uma TURMA");
        entity.setTeacher(teacher);
        Course course = courseService.findById(request.courseid());
        entity.setCourse(course);
        log.info("POST /turmas -> Cadastrada");
        Classroom response = super.service.save(entity);
        log.debug("POST /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Classroom> update(@Valid @RequestBody ClassroomUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /turmas/{}", id);
        Classroom entity = request.toEntity();
        entity.setId(id);
        log.info("PUT /turmas/{} -> Atualizada", id);
        Classroom response = super.service.save(entity);
        log.debug("PUT /turmas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<Classroom>> list() {
        log.info("GET /turmas -> Início");
        List<Classroom> entities = super.service.findAll();
        log.info("GET /turmas -> Encontrada(s) {} Turmas", entities.size());
        log.debug("GET /turmas -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(entities);
    }
}
