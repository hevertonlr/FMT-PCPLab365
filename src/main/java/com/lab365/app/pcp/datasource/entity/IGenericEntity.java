package com.lab365.app.pcp.datasource.entity;

public interface IGenericEntity<T> {
    Long getId();

    void update(T source);
}
