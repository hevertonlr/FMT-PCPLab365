package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;

import com.lab365.app.pcp.controller.dto.request.GradeRequestDTO;
import com.lab365.app.pcp.controller.dto.request.GradeUpdateDTO;
import com.lab365.app.pcp.controller.dto.response.GradeResponseDTO;
import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.enums.RolesEnum;
import com.lab365.app.pcp.infra.exception.InvalidException;
import com.lab365.app.pcp.infra.mapper.GradeMapper;
import com.lab365.app.pcp.service.interfaces.IGenericService;
import com.lab365.app.pcp.service.interfaces.IStudentService;
import com.lab365.app.pcp.service.interfaces.ISubjectService;
import com.lab365.app.pcp.service.interfaces.ITeacherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Tag(name = "Notas")
@Slf4j
@RestController
@RequestMapping(value = "notas", produces = MediaType.APPLICATION_JSON_VALUE)
public class GradeController extends GenericController<Grade, GradeResponseDTO> {

    private final IStudentService studentService;
    private final ITeacherService teacherService;
    private final ISubjectService subjectService;

    public GradeController(IGenericService<Grade> service,
            IStudentService studentService,
            ITeacherService teacherService,
            ISubjectService subjectService,
            GradeMapper modelMapper,
            GradeResponseDTO gradeResponse) {
        super(service, gradeResponse, modelMapper);
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @Operation(summary = "Criar", description = "Cria e atribui uma nota")
    @PostMapping
    public ResponseEntity<GradeResponseDTO> create(@Valid @RequestBody GradeRequestDTO request) {
        log.info("POST /notas");
        Grade entity = this.mapTo(request, Grade.class);
        Teacher teacher = teacherService.findById(request.getTeacherid());
        if (!Objects.equals(teacher.getUser().getRole().getName(), RolesEnum.TEACHER.toString()))
            throw new InvalidException("Somente um PROFESSOR pode ser atribuír uma NOTA");
        entity.setTeacher(teacher);
        Student student = studentService.findById(request.getStudentid());
        entity.setStudent(student);
        Subject subject = subjectService.findById(request.getSubjectid());
        if (!Objects.equals(student.getClassroom().getCourse().getId(), subject.getCourse().getId()))
            throw new InvalidException("ALUNO informado não registrado nesta MATÉRIA");

        if (!new HashSet<>(teacher.getClassrooms()).containsAll(subject.getCourse().getClassrooms()))
            throw new InvalidException("PROFESSOR informado não leciona um CURSO com esta MATÉRIA");

        entity.setSubject(subject);
        log.info("POST /notas -> Cadastrada");
        Grade savedEntity = super.service.save(entity);
        GradeResponseDTO response = this.mapTo(savedEntity, GradeResponseDTO.class);
        log.debug("POST /notas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza uma nota atribuída")
    @PutMapping("{id}")
    public ResponseEntity<GradeResponseDTO> update(@Valid @RequestBody GradeUpdateDTO request, @PathVariable Long id) {
        log.info("PUT /notas/{}", id);
        Grade entity = this.mapTo(request, Grade.class);
        entity.setId(id);
        log.info("PUT /notas/{} -> Atualizada", id);
        Grade savedEntity = super.service.save(entity);
        GradeResponseDTO response = this.mapTo(savedEntity, GradeResponseDTO.class);
        log.debug("PUT /notas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }
}
