package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import com.lab365.app.pcp.service.interfaces.IClassroomService;

import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl extends GenericServiceImpl<Classroom> implements IClassroomService {
    public ClassroomServiceImpl(IGenericRepository<Classroom> repository) {
        super(repository);
    }
}
