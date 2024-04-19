package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.entity.Subject;
import com.lab365.app.pcp.datasource.repository.GradeRepository;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class GradeService extends GenericService<Grade> {
    public GradeService(GradeRepository repository) {
        super(repository);
    }

    public List<Grade> findAllByStudentId(Long id) {
        List<Grade> entities = ((GradeRepository) super.repository).findAllByStudentId(id);
        if (entities.isEmpty())
            throw new NotFoundException("Nenhum registro a listar");

        log.info("Listando: {} Registro(s) encontrado(s)", entities.size());
        return entities;
    }

    public BigDecimal getScore(Long id) {
        List<Grade> grades = findAllByStudentId(id);
        log.info("Calculando Pontuação total -> INICIO");
        BigDecimal sumGrades = grades.stream().map(Grade::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("Calculando Pontuação total -> SOMA de notas: {}", sumGrades);
        long countSubjects = grades.stream().map(Grade::getSubject)
                .filter(Objects::nonNull).map(Subject::getName)
                .distinct().count();
        log.info("Calculando Pontuação total -> NUMERO de matérias: {}", countSubjects);
        BigDecimal average = sumGrades.divide(BigDecimal.valueOf(countSubjects), MathContext.DECIMAL128);
        log.info("Calculando Pontuação total -> DIVISÃO: {}", average);
        BigDecimal result = average.multiply(BigDecimal.TEN);
        log.info("Calculando Pontuação total -> RESULTADO FINAL: {}", result);
        return result;
    }
}
