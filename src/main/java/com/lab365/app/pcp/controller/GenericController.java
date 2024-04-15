package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.datasource.entity.GenericEntity;
import com.lab365.app.pcp.service.IGenericService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class GenericController<T extends GenericEntity<T>> implements IGenericController<T> {

    protected final IGenericService<T> service;

//    @Override
//    @PostMapping
//    public ResponseEntity<T> create(@RequestBody T entity) {
//        String requestedValue = "POST " + (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
//        log.info("{}", requestedValue);
//        entity = service.save(entity);
//        log.info("{} -> Cadastrado", requestedValue);
//        log.debug("{} -> Response Body:\n{}\n", requestedValue, toJSON(entity));
//        return ResponseEntity.status(HttpStatus.CREATED).body(entity); // Retorna 201
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public ResponseEntity<T> findById(@PathVariable Long id) {
//        String requestedValue = "GET " + (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
//        log.info("{} -> Início", requestedValue);
//        T entity = service.findById(id);
//        log.info("{} -> Encontrado", requestedValue);
//        log.debug("{} -> Response Body:\n{}\n", requestedValue, toJSON(entity));
//        return ResponseEntity.ok(entity);
//    }
//
//    @Override
//    @PutMapping()
//    public ResponseEntity<T> update(@RequestBody T entity) {
//        String requestedValue = "PUT " + (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
//        log.info("{}", requestedValue);
//        entity = service.save(entity);
//        log.info("{} -> Atualizado", requestedValue);
//        log.debug("{} -> Response Body:\n{}\n", requestedValue, toJSON(entity));
//        return ResponseEntity.ok(entity);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        String requestedValue = "DELETE " + (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
//        log.info("{}", requestedValue);
//        service.delete(id);
//        log.info("{} -> Excluído", requestedValue);
//        return ResponseEntity.noContent().build(); // Retorna 204
//    }
//
//    @Override
//    @GetMapping()
//    public ResponseEntity<List<T>> list() {
//        String requestedValue = "GET " + (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
//        log.info("{} -> Início", requestedValue);
//        List<T> entities = service.findAll();
//        log.info("{} -> Encontrados {} registros", requestedValue, entities.size());
//        log.debug("{} -> Response Body:\n{}\n", requestedValue, toJSON(entities));
//        return ResponseEntity.ok(entities);
//    }

}
