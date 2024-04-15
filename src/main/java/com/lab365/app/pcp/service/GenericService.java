package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.GenericEntity;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import com.lab365.app.pcp.infra.exception.InvalidException;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@Service
public abstract class GenericService<T extends GenericEntity<T>> implements IGenericService<T> {
    protected final IGenericRepository<T> repository;

    public GenericService(IGenericRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        List<T> entities = repository.findAll();
        if (entities.isEmpty())
            throw new NotFoundException("Nenhum registro a listar");

        log.info("Listando: {} Registro(s) encontrado(s)", entities.size());
        return entities;
    }

    @Override
    public T findById(Long id) throws NotFoundException {
        T entity = repository.findById(id).orElseThrow(
                () -> {
                    log.warn("Buscando: Registro com id ({}) -> NÃO ENCONTRADO!", id);
                    return new NotFoundException("Registro não encontrado com id: " + id);
                });

        log.info("Buscando: Registro ENCONTRADO -> \n{}\n", toJSON(entity));
        return entity;
    }

    @Override
    public T save(T entity) {
        try {
            String action = entity.getId() == null ? "criado" : "alterado";
            Optional<T> existent = repository.findById(entity.getId());
            if (existent.isPresent()) {
                existent.get().update(entity);
                entity = existent.get();
            }
            T finalEntity = repository.save(entity);

            log.info("Salvando: Registro {} com sucesso.", action);
            log.info("Salvando: Registro {} -> \n{}\n", action, toJSON(entity));
            return finalEntity;
        } catch (DataAccessException e) {
            log.error("Salvando: ERRO -> {}", e.getMessage());
            throw new InvalidException(e.getMessage());
        }

    }

    @Override
    public void delete(Long id) throws NotFoundException {
        repository.findById(id).ifPresentOrElse(repository::delete,
                () -> {
                    log.warn("Deletando: Registro com id ({}) -> NÃO ENCONTRADO!", id);
                    throw new NotFoundException("Registro não encontrado com id: " + id);
                });
        log.info("Deletando: Registro com id ({}) -> Excluído com sucesso", id);
    }
}
