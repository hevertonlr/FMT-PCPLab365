package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.lab365.app.pcp.controller.dto.request.ClassroomRequestDTO;
import com.lab365.app.pcp.controller.dto.request.ClassroomUpdateDTO;
import com.lab365.app.pcp.controller.dto.response.ClassroomResponseDTO;
import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.infra.mapper.ClassroomMapper;
import com.lab365.app.pcp.service.interfaces.ICourseService;
import com.lab365.app.pcp.service.interfaces.IGenericService;

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

@Tag(name = "Turmas")
@Slf4j
@RestController
@RequestMapping(value = "turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClassroomController extends GenericController<Classroom, ClassroomResponseDTO> {
    // private final IGenericService<Teacher> teacherService;
    private final ICourseService courseService;

    public ClassroomController(IGenericService<Classroom> service,
            IGenericService<Teacher> teacherService,
            ICourseService courseService,
            ClassroomResponseDTO classroomResponse,
            ClassroomMapper modelMapper) {
        super(service, classroomResponse, modelMapper);
        // this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @Operation(summary = "Criar", description = "Cria uma turma")
    @PostMapping
    public ResponseEntity<ClassroomResponseDTO> create(@Valid @RequestBody ClassroomRequestDTO request) {
        log.info("POST /turmas");
        Classroom entity = this.mapTo(request, Classroom.class);
        // Teacher teacher = teacherService.findById(request.teacherid());
        // if (!Objects.equals(teacher.getUser().getRole().getName(),
        // RolesEnum.PROFESSOR.toString()))
        // throw new InvalidException("Somente um PROFESSOR pode ser atribuído a uma
        // TURMA");
        // entity.setTeacher(teacher);
        Course course = courseService.findById(request.getCourseid());
        entity.setCourse(course);
        log.info("POST /turmas -> Cadastrada");
        Classroom savedEntity = super.service.save(entity);
        ClassroomResponseDTO response = this.mapTo(savedEntity, ClassroomResponseDTO.class);
        log.debug("POST /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza uma turma identificada por ID")
    @PutMapping("{id}")
    public ResponseEntity<ClassroomResponseDTO> update(@Valid @RequestBody ClassroomUpdateDTO request,
            @PathVariable Long id) {
        log.info("PUT /turmas/{}", id);
        Classroom entity = this.mapTo(request, Classroom.class);
        entity.setId(id);
        log.info("PUT /turmas/{} -> Atualizada", id);
        Classroom savedEntity = super.service.save(entity);
        ClassroomResponseDTO response = this.mapTo(savedEntity, ClassroomResponseDTO.class);
        log.debug("PUT /turmas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Listar todas as turmas")
    @GetMapping()
    public ResponseEntity<List<ClassroomResponseDTO>> list() {

        log.info("GET /turmas -> Início");
        List<Classroom> entities = super.service.findAll();
        log.info("GET /turmas -> Encontrada(s) {} Turmas", entities.size());
        List<ClassroomResponseDTO> response = entities.stream()
                .map(entity -> (ClassroomResponseDTO) this.mapTo(entity, ClassroomResponseDTO.class)).toList();
        log.debug("GET /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.ok(response);
    }
}
