package com.lab365.app.pcp.datasource.repository;

import java.util.Optional;

import com.lab365.app.pcp.datasource.entity.Teacher;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends IGenericRepository<Teacher> {

        @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "Teacher.withAddressAndUser")
        Optional<Teacher> findWithAddressAndUserById(Long id);

}
