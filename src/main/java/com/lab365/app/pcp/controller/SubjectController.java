package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.lab365.app.pcp.controller.dto.request.SubjectRequestDTO;
import com.lab365.app.pcp.controller.dto.response.SubjectResponseDTO;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.infra.mapper.SubjectMapper;
import com.lab365.app.pcp.service.interfaces.ICourseService;
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

@Tag(name = "Matérias")
@Slf4j
@RestController
@RequestMapping(value = "materias", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController extends GenericController<Subject, SubjectResponseDTO> {
    private final ICourseService courseService;

    public SubjectController(ISubjectService service, ICourseService courseService, SubjectResponseDTO subjectResponse,
            SubjectMapper modelMapper) {
        super(service, subjectResponse, modelMapper);
        this.courseService = courseService;
    }

    @Operation(summary = "Criar", description = "Cria uma nova matéria")
    @PostMapping
    public ResponseEntity<SubjectResponseDTO> create(@Valid @RequestBody SubjectRequestDTO request) {
        log.info("POST /materias");
        Subject entity = this.mapTo(request, Subject.class);
        Course course = courseService.findById(request.getCourseid());
        entity.setCourse(course);
        Subject savedEntity = super.service.save(entity);
        log.info("POST /materias -> Cadastrada");
        SubjectResponseDTO response = this.mapTo(savedEntity, SubjectResponseDTO.class);
        log.debug("POST /materias -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Listar todas as turmas")
    @PutMapping("{id}")
    public ResponseEntity<SubjectResponseDTO> update(@Valid @RequestBody SubjectRequestDTO request,
            @PathVariable Long id) {
        log.info("PUT /materias/{}", id);
        Subject entity = this.mapTo(request, Subject.class);
        entity.setId(id);
        log.info("PUT /materias/{} -> Atualizada", id);
        Subject savedEntity = super.service.save(entity);
        SubjectResponseDTO response = this.mapTo(savedEntity, SubjectResponseDTO.class);
        log.debug("PUT /materias/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Listar todas as materias")
    @GetMapping()
    public ResponseEntity<List<SubjectResponseDTO>> list() {
        log.info("GET /turmas -> Início");
        List<Subject> entities = super.service.findAll();
        log.info("GET /turmas -> Encontrada(s) {} Turmas", entities.size());
        List<SubjectResponseDTO> response = entities.stream()
                .map(entity -> (SubjectResponseDTO) this.mapTo(entity, SubjectResponseDTO.class)).toList();
        log.debug("GET /turmas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.ok(response);
    }
}
