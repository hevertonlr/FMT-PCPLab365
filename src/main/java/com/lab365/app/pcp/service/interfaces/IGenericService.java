package com.lab365.app.pcp.service.interfaces;

import java.util.List;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IGenericService<T extends IGenericEntity<T>> {
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    void delete(Long id);
}
