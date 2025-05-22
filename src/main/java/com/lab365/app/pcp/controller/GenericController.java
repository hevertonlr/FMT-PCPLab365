package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

import com.lab365.app.pcp.controller.dto.request.PageableParams;
import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import com.lab365.app.pcp.infra.mapper.IGenericMapper;
import com.lab365.app.pcp.service.interfaces.IGenericService;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.lab365.app.pcp.infra.utils.Util.buildPageable;
import static com.lab365.app.pcp.infra.utils.Util.buildSpec;
import static com.lab365.app.pcp.infra.utils.Util.getPathMethod;

@Slf4j
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class GenericController<T extends IGenericEntity<T>, R> implements IGenericController<T, R> {
    protected final IGenericService<T> service;
    protected final R responseDTO;
    protected final IGenericMapper<T, R> mapper;

    @Override
    @Operation(summary = "Buscar", description = "Busca uma entidade específica pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<R> findById(@PathVariable Long id) {
        String methodPath = getPathMethod();
        log.info("GET {} -> Início", methodPath);
        T entity = service.findById(id);
        log.info("GET {} -> Encontrado(a)", methodPath);
        R response = mapper.toDto(entity);
        log.debug("GET {} -> Response Body:\n{}\n", methodPath, response);
        return ResponseEntity.ok(response);
    }

    @Override
    @Operation(summary = "Deletar", description = "Deleta uma entidade específica pelo ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        String methodPath = getPathMethod();
        log.info("DELETE {}", methodPath);
        service.delete(id);
        log.info("DELETE {} -> Excluído(a)", methodPath);
        return ResponseEntity.noContent().build();
    }

    // private Type getResponseDTOType() {
    // return ((ParameterizedType)
    // getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    // }

    // protected TypeToken<R> getResponseTypeToken() {
    // return new TypeToken<R>() {
    // };
    // }

    // protected R mapToDto(T source, TypeToken<R> destinationType) {
    // try {
    // return mapper.map(source, destinationType.getType());
    // } catch (Exception e) {
    // throw new IllegalArgumentException(
    // "Failed to map from " + source.getClass().getSimpleName() +
    // " to " + destinationType.getType().getTypeName(),
    // e);
    // }
    // }

    protected <R> R mapTo(Object source, Class<R> targetClass) {
        return mapper.map(source, targetClass);
    }

    @SuppressWarnings("unchecked")
    protected <R> List<R> mapToList(Collection<?> source, Class<R> elementClass) {
        return mapper.map(source, List.class);
    }

    @Operation(summary = "Listar Paginado", description = "Lista itens de forma paginada")
    @GetMapping("paged")
    public Page<R> listPaged(@Valid PageableParams params) {

        return service.findAll(
                buildSpec(params.getFilters()),
                buildPageable(params)).map(mapper::toDto);
    }
}
