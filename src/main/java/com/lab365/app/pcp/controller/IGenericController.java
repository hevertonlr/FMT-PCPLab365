package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import org.springframework.http.ResponseEntity;

public interface IGenericController<T extends IGenericEntity<T>> {
    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> list();

    ResponseEntity<?> create(T entity);

    ResponseEntity<?> update(T entity);

    ResponseEntity<?> delete(Long id);
}
