package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends GenericService<Course> {
    public CourseService(CourseRepository repository) {
        super(repository);
    }
}
