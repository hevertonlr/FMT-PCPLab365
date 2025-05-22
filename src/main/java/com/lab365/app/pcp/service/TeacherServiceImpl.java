package com.lab365.app.pcp.service;

import lombok.extern.slf4j.Slf4j;

import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.repository.TeacherRepository;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import com.lab365.app.pcp.service.interfaces.ITeacherService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class TeacherServiceImpl extends GenericServiceImpl<Teacher> implements ITeacherService {
    private final TeacherRepository _repository;

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
        this._repository = repository;
    }

    public Page<Teacher> findAll(Specification<Teacher> spec, Pageable pageable) {
        return _repository.findAll(spec, pageable);
    }

    @Override
    public Page<Teacher> findAllWithRelations(Pageable pageable) {
        return _repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Teacher findByIdWithUserAndAddress(Long id) {
        return _repository.findWithAddressAndUserById(id)
                .orElseThrow(() -> {
                    log.warn("Buscando: Professor com id ({}) -> NÃO ENCONTRADO!", id);
                    return new NotFoundException(String.format("Professor não encontrado com id: {0}", id));
                });
    }

    @Transactional(readOnly = true)
    public Teacher findByIdWithAllDetails(Long id) {
        return _repository.findWithAddressAndUserById(id)
                .orElseThrow(() -> {
                    log.warn("Buscando: Professor com id ({}) -> NÃO ENCONTRADO!", id);
                    return new NotFoundException(String.format("Professor não encontrado com id: {0}", id));
                });
    }
}
