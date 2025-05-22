package com.lab365.app.pcp.service.interfaces;

import com.lab365.app.pcp.datasource.entity.Teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ITeacherService extends IGenericService<Teacher> {

    Page<Teacher> findAll(Specification<Teacher> spec, Pageable pageable);

    Page<Teacher> findAllWithRelations(Pageable pageable);

    Teacher findByIdWithAllDetails(Long id);

}
