package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import org.springframework.http.ResponseEntity;

public interface IGenericController<T extends IGenericEntity<T>> {
    //
    ResponseEntity<T> findById(Long id);
//
//    ResponseEntity<List<T>> list();
//
//    ResponseEntity<T> create(T entity);
//
//    ResponseEntity<T> update(T entity);

    ResponseEntity<Void> delete(Long id);
}
