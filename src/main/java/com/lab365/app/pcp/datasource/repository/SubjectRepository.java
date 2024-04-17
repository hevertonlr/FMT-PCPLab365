package com.lab365.app.pcp.datasource.repository;

import com.lab365.app.pcp.datasource.entity.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends IGenericRepository<Subject> {
    List<Subject> findAllByCourseId(Long courseId);
}
