package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends GenericService<Course> {
    public CourseService(IGenericRepository<Course> repository) {
        super(repository);
    }
}
