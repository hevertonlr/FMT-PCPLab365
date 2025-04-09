package com.lab365.app.pcp.controller.dto.request;

public interface IGenericRequestDTO<T>{
    T toEntity();
}
