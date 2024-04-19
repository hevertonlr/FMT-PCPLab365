package com.lab365.app.pcp.datasource.repository;

import com.lab365.app.pcp.datasource.entity.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends IGenericRepository<Grade> {
    List<Grade> findAllByStudentId(Long id);
}
