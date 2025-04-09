package com.lab365.app.pcp.service;

import lombok.extern.slf4j.Slf4j;

import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.repository.TeacherRepository;
import com.lab365.app.pcp.infra.exception.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@Transactional
public class TeacherService extends GenericService<Teacher> {
    private final TeacherRepository _repository;

    public TeacherService(TeacherRepository repository) {
        super(repository);
        this._repository = repository;
    }

    @Transactional(readOnly = true)
    public Teacher findByIdWithAddress(Long id) {
        return _repository.findWithDetailsById(id)
                .orElseThrow(() -> {
                    log.warn("Buscando: Professor com id ({}) -> NÃO ENCONTRADO!", id);
                    return new NotFoundException(format("Professor não encontrado com id: {0}", id));
                });
    }

    @Transactional(readOnly = true)
    public Teacher findByIdWithAllDetails(Long id) {
        return _repository.findWithAllDetailsById(id)
                .orElseThrow(() -> {
                    log.warn("Buscando: Professor com id ({}) -> NÃO ENCONTRADO!", id);
                    return new NotFoundException(format("Professor não encontrado com id: {0}", id));
                });
    }
}
