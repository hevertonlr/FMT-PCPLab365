package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.datasource.repository.SubjectRepository;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SubjectService extends GenericService<Subject> {
    public SubjectService(SubjectRepository repository) {
        super(repository);
    }

    public List<Subject> findAllByCourseId(Long id) {
        List<Subject> entities = ((SubjectRepository) super.repository).findAllByCourseId(id);
        if (entities.isEmpty())
            throw new NotFoundException("Nenhuma Matéria a listar");

        log.info("Listando: {} Matéria(s) encontrada(s)", entities.size());
        return entities;
    }
}
