package com.lab365.app.pcp.service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.datasource.repository.SubjectRepository;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import com.lab365.app.pcp.service.interfaces.ISubjectService;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubjectServiceImpl extends GenericServiceImpl<Subject> implements ISubjectService {
    public SubjectServiceImpl(SubjectRepository repository) {
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
