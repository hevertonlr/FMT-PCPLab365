package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.datasource.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends GenericService<Teacher> {
    public TeacherService(TeacherRepository repository) {
        super(repository);
    }
}
