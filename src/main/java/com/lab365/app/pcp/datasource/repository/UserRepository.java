package com.lab365.app.pcp.datasource.repository;

import com.lab365.app.pcp.datasource.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends IGenericRepository<User> {
    Optional<User> findByUsername(String username);
}
