package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.datasource.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService extends GenericService<Subject> {
    public SubjectService(SubjectRepository repository) {
        super(repository);
    }
}
