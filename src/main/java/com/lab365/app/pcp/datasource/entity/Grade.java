package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "notas")
public class Grade extends GenericEntity<Grade> {
    @ColumnDefault(value = "0.00")
    @Column(name = "valor", precision = 5, scale = 2, nullable = false)
    private BigDecimal value;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "data")
    private LocalDate date;

    @ManyToOne
    @JsonIgnoreProperties("grades")
    @JoinColumn(name = "id_aluno", nullable = false)
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("grades")
    @JoinColumn(name = "id_professor")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnoreProperties("grades")
    @JoinColumn(name = "id_materia", nullable = false)
    private Subject subject;

    @Override
    public Grade update(Grade source) {
        if (!source.getValue().equals(BigDecimal.ZERO)) setValue(source.getValue());
        if (source.getDate() != null) setDate(source.getDate());
        if (source.getStudent() != null) setStudent(source.getStudent());
        if (source.getTeacher() != null) setTeacher(source.getTeacher());
        if (source.getSubject() != null) setSubject(source.getSubject());
        return source;
    }
}
