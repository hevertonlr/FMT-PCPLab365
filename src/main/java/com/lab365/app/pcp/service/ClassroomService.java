package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService extends GenericService<Classroom> {
    public ClassroomService(ClassroomRepository repository) {
        super(repository);
    }
}
