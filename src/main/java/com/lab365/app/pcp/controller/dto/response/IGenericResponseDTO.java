package com.lab365.app.pcp.controller.dto.response;

import org.springframework.stereotype.Component;

@Component
public interface IGenericResponseDTO<T,D> {
    D fromEntity(T entity);
}
