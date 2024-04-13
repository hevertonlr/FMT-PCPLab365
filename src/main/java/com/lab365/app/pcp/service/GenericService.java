package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public abstract class GenericService<T extends IGenericEntity<T>> implements IGenericService<T> {
    protected final IGenericRepository<T> repository;
    public GenericService(IGenericRepository<T> repository){
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Entidade não encontrada com id: " + id);
        });
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            throw new RuntimeException("Entidade não encontrada com id: " + id);
                        });
    }
}
