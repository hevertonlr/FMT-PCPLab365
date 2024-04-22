package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Grade;
import com.lab365.app.pcp.datasource.repository.GradeRepository;
import com.lab365.app.pcp.infra.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GradeService extends GenericService<Grade> {
    public GradeService(GradeRepository repository) {
        super(repository);
    }

    public List<Grade> findAllByStudentId(Long id) {
        List<Grade> entities = ((GradeRepository) super.repository).findAllByStudentId(id);
        if (entities.isEmpty())
            throw new NotFoundException("Nenhuma Nota a listar");

        log.info("Listando: {} Nota(s) encontrada(s)", entities.size());
        return entities;
    }

    public Map<Long, BigDecimal> getScore(Long id) {
        List<Grade> grades = findAllByStudentId(id);
        log.info("Calculando Pontuação total por curso para o aluno -> INICIO");

        return grades.stream()
                .collect(Collectors.groupingBy(grade -> grade.getSubject().getCourse().getId()))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            List<Grade> courseGrades = entry.getValue();
                            BigDecimal sumGrades = courseGrades.stream()
                                    .map(Grade::getValue)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                            log.info("Curso ID: {}, SOMA de notas: {}", entry.getKey(), sumGrades);

                            long countSubjects = courseGrades.stream()
                                    .map(Grade::getSubject)
                                    .filter(Objects::nonNull)
                                    .distinct()
                                    .count();
                            log.info("Curso ID: {}, NUMERO de matérias: {}", entry.getKey(), countSubjects);

                            BigDecimal average = countSubjects > 0 ? sumGrades.divide(BigDecimal.valueOf(countSubjects), 2, RoundingMode.HALF_UP)
                                    : BigDecimal.ZERO;
                            log.info("Curso ID: {}, MÉDIA: {}", entry.getKey(), average);

                            BigDecimal totalScore = average.multiply(BigDecimal.TEN);
                            log.info("Curso ID: {}, PONTUAÇÃO TOTAL: {}", entry.getKey(), totalScore);

                            return totalScore;
                        }
                ));
    }
}
