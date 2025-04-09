package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.ClassroomCreateRequest;
import com.lab365.app.pcp.controller.dto.request.ClassroomUpdateRequest;
import com.lab365.app.pcp.controller.dto.response.ClassroomResponse;
import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.service.IGenericService;
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

@Tag(name = "Turmas")
@Slf4j
@RestController
@RequestMapping(value = "turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClassroomController extends GenericController<Classroom, ClassroomResponse> {
    // private final IGenericService<Teacher> teacherService;
    private final IGenericService<Course> courseService;
    private final ClassroomResponse classroomResponse;

    public ClassroomController(IGenericService<Classroom> service,
            IGenericService<Teacher> teacherService,
            IGenericService<Course> courseService,
            ClassroomResponse classroomResponse) {
        super(service, classroomResponse);
        // this.teacherService = teacherService;
        this.courseService = courseService;
        this.classroomResponse = classroomResponse;
    }

    @Operation(summary = "Criar", description = "Cria uma turma")
    @PostMapping
    public ResponseEntity<ClassroomResponse> create(@Valid @RequestBody ClassroomCreateRequest request) {
        log.info("POST /turmas");
        Classroom entity = request.toEntity();
        // Teacher teacher = teacherService.findById(request.teacherid());
        // if (!Objects.equals(teacher.getUser().getRole().getName(),
        // RolesEnum.PROFESSOR.toString()))
        // throw new InvalidException("Somente um PROFESSOR pode ser atribuído a uma
        // TURMA");
        // entity.setTeacher(teacher);
        Course course = courseService.findById(request.courseid());
        entity.setCourse(course);
        log.info("POST /turmas -> Cadastrada");
        Classroom savedEntity = super.service.save(entity);
        ClassroomResponse response = classroomResponse.fromEntity(savedEntity);
        log.debug("POST /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza uma turma identificada por ID")
    @PutMapping("{id}")
    public ResponseEntity<ClassroomResponse> update(@Valid @RequestBody ClassroomUpdateRequest request,
            @PathVariable Long id) {
        log.info("PUT /turmas/{}", id);
        Classroom entity = request.toEntity();
        entity.setId(id);
        log.info("PUT /turmas/{} -> Atualizada", id);
        Classroom savedEntity = super.service.save(entity);
        ClassroomResponse response = classroomResponse.fromEntity(savedEntity);
        log.debug("PUT /turmas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Listar todas as turmas")
    @GetMapping()
    public ResponseEntity<List<ClassroomResponse>> list() {
        log.info("GET /turmas -> Início");
        List<Classroom> entities = super.service.findAll();
        log.info("GET /turmas -> Encontrada(s) {} Turmas", entities.size());
        List<ClassroomResponse> response = entities.stream().map(classroomResponse::fromEntity).toList();
        log.debug("GET /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.ok(response);
    }
}
