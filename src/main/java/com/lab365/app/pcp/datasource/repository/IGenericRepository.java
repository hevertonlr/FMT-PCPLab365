package com.lab365.app.pcp.datasource.repository;

import com.lab365.app.pcp.datasource.entity.IGenericEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
@NoRepositoryBean
@Qualifier("IGenericRepository")
public interface IGenericRepository<T extends IGenericEntity<T>> extends JpaRepository<T, Long> {
}
