package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.lab365.app.pcp.controller.dto.request.CourseRequestDTO;
import com.lab365.app.pcp.controller.dto.response.CourseResponseDTO;
import com.lab365.app.pcp.controller.dto.response.SubjectResponseDTO;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.infra.mapper.CourseMapper;
import com.lab365.app.pcp.service.interfaces.IGenericService;
import com.lab365.app.pcp.service.interfaces.ISubjectService;

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

@Tag(name = "Cursos")
@Slf4j
@RestController
@RequestMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController extends GenericController<Course, CourseResponseDTO> {
    private final ISubjectService subjectService;

    public CourseController(IGenericService<Course> service,
            ISubjectService subjectService,
            CourseResponseDTO courseResponse,
            SubjectResponseDTO subjectResponse,
            CourseMapper modelMapper) {
        super(service, courseResponse, modelMapper);
        this.subjectService = subjectService;
    }

    @Operation(summary = "Criar", description = "Cria um curso")
    @PostMapping
    public ResponseEntity<CourseResponseDTO> create(@Valid @RequestBody CourseRequestDTO request) {
        log.info("POST /cursos");
        Course entity = super.service.save(this.mapTo(request, Course.class));
        CourseResponseDTO response = this.mapTo(entity, CourseResponseDTO.class);
        log.info("POST /cursos -> Cadastrado");
        log.debug("POST /cursos -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza um curso pelo ID")
    @PutMapping("{id}")
    public ResponseEntity<CourseResponseDTO> update(@Valid @RequestBody CourseRequestDTO request,
            @PathVariable Long id) {
        log.info("PUT /cursos/{}", id);
        Course entity = this.mapTo(request, Course.class);
        entity.setId(id);
        log.info("PUT /cursos/{} -> Atualizado", id);
        Course savedEntity = super.service.save(entity);
        CourseResponseDTO response = this.mapTo(savedEntity, CourseResponseDTO.class);
        log.debug("PUT /cursos/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar Matérias", description = "Lista as matérias de um Curso pelo ID")
    @GetMapping(value = "{id}/materias")
    public ResponseEntity<List<SubjectResponseDTO>> list(@PathVariable Long id) {
        log.info("GET /cursos/{}/materias", id);
        List<Subject> entities = subjectService.findAllByCourseId(id);
        log.info("GET /cursos/{}/materias -> Encontrado(s) {} Curso(s)", id, entities.size());
        List<SubjectResponseDTO> response = this.mapToList(entities, SubjectResponseDTO.class);
        log.debug("GET /cursos/{}/materias -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Lista todos os cursos cadastrados")
    @GetMapping()
    public ResponseEntity<List<CourseResponseDTO>> list() {
        log.info("GET /cursos -> Início");
        List<Course> entities = super.service.findAll();
        log.info("GET /cursos -> Encontrados {} registros", entities.size());
        List<CourseResponseDTO> response = this.mapToList(entities, CourseResponseDTO.class);
        log.debug("GET /cursos -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(response);
    }

}
