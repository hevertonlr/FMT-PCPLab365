package com.lab365.app.pcp.service.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.lab365.app.pcp.datasource.entity.Grade;

public interface IGradeService extends IGenericService<Grade> {

    List<Grade> findAllByStudentId(Long id);

    Map<Long, BigDecimal> getScore(Long id);

}
