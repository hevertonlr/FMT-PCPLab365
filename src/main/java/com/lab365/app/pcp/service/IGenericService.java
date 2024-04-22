package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;

import java.util.List;

public interface IGenericService<T extends IGenericEntity<T>> {
    List<T> findAll();
    T findById(Long id);
    T save(T entity);
    void delete(Long id);
}
