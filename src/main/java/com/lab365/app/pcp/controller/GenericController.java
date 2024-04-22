package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import com.lab365.app.pcp.service.IGenericService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.lab365.app.pcp.infra.utils.Util.getPathMethod;
import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class GenericController<T extends IGenericEntity<T>> implements IGenericController<T> {
    protected final IGenericService<T> service;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        String methodPath = getPathMethod();
        log.info("GET {} -> Início", methodPath);
        T entity = service.findById(id);
        log.info("GET {} -> Encontrado(a)", methodPath);
        log.debug("GET {} -> Response Body:\n{}\n", methodPath, toJSON(entity));
        return ResponseEntity.ok(entity);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        String methodPath = getPathMethod();
        log.info("DELETE {}", methodPath);
        service.delete(id);
        log.info("DELETE {} -> Excluído(a)", methodPath);
        return ResponseEntity.noContent().build();
    }

}
