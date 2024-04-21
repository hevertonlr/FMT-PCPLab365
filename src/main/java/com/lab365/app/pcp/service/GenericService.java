package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.GenericEntity;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import com.lab365.app.pcp.infra.exception.InvalidException;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;
import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class GenericService<T extends GenericEntity<T>> implements IGenericService<T> {
    protected final IGenericRepository<T> repository;
    protected String entityName = ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0]).getAnnotation(Entity.class).name();

    @Override
    public List<T> findAll() {
        List<T> entities = repository.findAll();
        if (entities.isEmpty())
            throw new NotFoundException(format("Nenhum(a) {0} a listar", entityName));

        log.info("Listando: {} {}(s) encontrados(as)", entities.size(), entityName);
        return entities;
    }

    @Override
    public T findById(Long id) throws NotFoundException {
        T entity = repository.findById(id).orElseThrow(
                () -> {
                    log.warn("Buscando: {} com id ({}) -> NÃO ENCONTRADO!", entityName, id);
                    return new NotFoundException(format("{0} não encontrado(a) com id: {1}", entityName, id));
                });

        log.info("Buscando: {} encontrado(a) -> \n{}\n", entityName, toJSON(entity));
        return entity;
    }

    @Override
    public T save(T source) {
        try {
            boolean isCreation = source.getId() == null;
            T entity = isCreation ? source : findById(source.getId());
            if (!isCreation) entity.update(source);

            T finalEntity = repository.save(entity);

            String action = isCreation ? "criado" : "alterado";
            log.info("Salvando: {} {} com sucesso.", entityName, action);
            log.info("Salvando: {} {} -> \n{}\n", entityName, action, toJSON(finalEntity));
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
                    log.warn("Deletando: {} com id ({}) -> NÃO ENCONTRADO(A)!", entityName, id);
                    throw new NotFoundException(format("{0} não encontrado(a) com id: {1}", entityName, id));
                });
        log.info("Deletando: {} com id ({}) -> Excluído(a) com sucesso", entityName, id);
    }
}
