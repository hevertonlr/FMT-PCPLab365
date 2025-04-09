package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.controller.dto.response.IGenericResponseDTO;
import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import com.lab365.app.pcp.service.IGenericService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.lab365.app.pcp.infra.utils.Util.getPathMethod;

@Slf4j
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class GenericController<T extends IGenericEntity<T>, D extends IGenericResponseDTO<T, D>>
        implements IGenericController<T, D> {
    protected final IGenericService<T> service;
    protected final D responseDTO;

    @Override
    @Operation(summary = "Buscar", description = "Busca uma entidade específica pelo ID")
    @GetMapping("{id}")
    public ResponseEntity<D> findById(@PathVariable Long id) {
        String methodPath = getPathMethod();
        log.info("GET {} -> Início", methodPath);
        T entity = service.findById(id);
        log.info("GET {} -> Encontrado(a)", methodPath);
        D response = responseDTO.fromEntity(entity);
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

}
