package com.lab365.app.pcp.datasource.repository;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
@NoRepositoryBean
@Qualifier("IGenericRepository")
public interface IGenericRepository<T extends IGenericEntity<T>>
        extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    @Override
    @NonNull
    Page<T> findAll(@NonNull Pageable pageable);
}
