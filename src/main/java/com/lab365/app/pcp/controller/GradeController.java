package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.request.GradeCreateRequest;
import com.lab365.app.pcp.controller.dto.request.GradeUpdateRequest;
import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.datasource.entity.Subject;
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

import java.util.Objects;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@RestController
@RequestMapping(value = "notas", produces = MediaType.APPLICATION_JSON_VALUE)
public class GradeController extends GenericController<Grade> {

    private final IGenericService<Student> studentService;
    private final IGenericService<Teacher> teacherService;
    private final IGenericService<Subject> subjectService;

    public GradeController(IGenericService<Grade> service,
                           IGenericService<Student> studentService,
                           IGenericService<Teacher> teacherService,
                           IGenericService<Subject> subjectService) {
        super(service);
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<Grade> create(@Valid @RequestBody GradeCreateRequest request) {
        log.info("POST /notas");
        Grade entity = request.toEntity();
        Teacher teacher = teacherService.findById(request.teacherid());
        if (!Objects.equals(teacher.getUser().getRole().getName(), RolesEnum.PROFESSOR.toString()))
            throw new InvalidException("Somente um PROFESSOR pode ser atribuír uma NOTA");
        entity.setTeacher(teacher);
        Student student = studentService.findById(request.studentid());
        entity.setStudent(student);
        Subject subject = subjectService.findById(request.subjectid());
        entity.setSubject(subject);
        log.info("POST /notas -> Cadastrada");
        Grade response = super.service.save(entity);
        log.debug("POST /notas -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Grade> update(@Valid @RequestBody GradeUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /notas/{}", id);
        Grade entity = request.toEntity();
        entity.setId(id);
        log.info("PUT /notas/{} -> Atualizada", id);
        Grade response = super.service.save(entity);
        log.debug("PUT /notas/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }
}
