package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends GenericService<Student> {
    public StudentService(IGenericRepository<Student> repository) {
        super(repository);
    }
}
