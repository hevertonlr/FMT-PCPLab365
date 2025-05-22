package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;

import org.springframework.http.ResponseEntity;

public interface IGenericController<T extends IGenericEntity<T>, R> {

    ResponseEntity<R> findById(Long id);

    ResponseEntity<Void> delete(Long id);
}
