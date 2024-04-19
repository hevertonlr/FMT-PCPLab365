package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService extends GenericService<Classroom> {
    public ClassroomService(IGenericRepository<Classroom> repository) {
        super(repository);
    }
}
