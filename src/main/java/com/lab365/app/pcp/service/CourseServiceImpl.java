package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import com.lab365.app.pcp.service.interfaces.ICourseService;

import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends GenericServiceImpl<Course> implements ICourseService {
    public CourseServiceImpl(IGenericRepository<Course> repository) {
        super(repository);
    }
}
