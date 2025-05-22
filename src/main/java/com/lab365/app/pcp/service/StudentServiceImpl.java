package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Student;
import com.lab365.app.pcp.datasource.repository.StudentRepository;
import com.lab365.app.pcp.service.interfaces.IStudentService;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends GenericServiceImpl<Student> implements IStudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

}
