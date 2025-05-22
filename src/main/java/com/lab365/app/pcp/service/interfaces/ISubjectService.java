package com.lab365.app.pcp.service.interfaces;

import java.util.List;

import com.lab365.app.pcp.datasource.entity.Subject;

public interface ISubjectService extends IGenericService<Subject> {

    List<Subject> findAllByCourseId(Long id);

}
