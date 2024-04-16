package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.repository.GradeRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeService extends GenericService<Grade> {
    public GradeService(GradeRepository repository) {
        super(repository);
    }
}
