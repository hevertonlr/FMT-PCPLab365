package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@Service
public abstract class GenericService<T extends IGenericEntity<T>> implements IGenericService<T> {
    protected final IGenericRepository<T> repository;

    public GenericService(IGenericRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        List<T> entities = repository.findAll();
        if(entities.isEmpty())
            throw new NotFoundException("Nenhum registro a listar");

        log.info("Listando: {} Registro(s) encontrado(s)", entities.size());
        return entities;
    }

    @Override
    public T findById(Long id) throws NotFoundException{
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
        String action = entity.getId() == null ? "criado" : "alterado";
        T finalEntity = repository.save(entity);

        log.info("Salvando: Registro {} com sucesso.", action);
        log.info("Salvando: Registro {} -> \n{}\n", action, toJSON(entity));
        return finalEntity;
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
