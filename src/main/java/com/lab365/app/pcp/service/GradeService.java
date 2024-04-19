package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeService extends GenericService<Grade> {
    public GradeService(IGenericRepository<Grade> repository) {
        super(repository);
    }
}
