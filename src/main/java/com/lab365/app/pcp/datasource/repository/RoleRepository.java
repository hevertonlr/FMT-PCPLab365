package com.lab365.app.pcp.datasource.repository;

import com.lab365.app.pcp.datasource.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends IGenericRepository<Role>{
    Optional<Role> findByName(String name);
}
