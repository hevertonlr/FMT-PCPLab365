package com.lab365.app.pcp.datasource.repository;

import com.lab365.app.pcp.datasource.entity.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends IGenericRepository<Teacher> {
    @EntityGraph(attributePaths = {"address", "user"})
    Optional<Teacher> findWithDetailsById(Long id);

    @EntityGraph(attributePaths = {"address", "user", "user.role", "subjects"})
    Optional<Teacher> findWithAllDetailsById(Long id);
}
