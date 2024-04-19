package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.datasource.repository.SubjectRepository;
import com.lab365.app.pcp.infra.exception.InvalidException;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SubjectService extends GenericService<Subject> {
    private final CourseService courseService;

    public SubjectService(SubjectRepository repository, CourseService courseService) {
        super(repository);
        this.courseService = courseService;
    }


    public List<Subject> findAllByCourseId(Long id) {
        List<Subject> entities = ((SubjectRepository) super.repository).findAllByCourseId(id);
        if (entities.isEmpty())
            throw new NotFoundException("Nenhum registro a listar");

        log.info("Listando: {} Registro(s) encontrado(s)", entities.size());
        return entities;
    }

    @Override
    public Subject save(Subject entity) {
        Long courseId = entity.getCourse() != null ? entity.getCourse().getId() : null;
        if (courseId == null)
            throw new InvalidException("ID de curso não pode ser null");

        Course course = courseService.findById(courseId);
        entity.setCourse(course);
        return super.save(entity);
    }
}
