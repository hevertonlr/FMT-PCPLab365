package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends GenericService<Teacher> {
    public TeacherService(IGenericRepository<Teacher> repository) {
        super(repository);
    }
}
