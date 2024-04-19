package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "materia")
public class Subject extends GenericEntity<Subject> {
    @Column(name = "nome")
    private String name;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties("subjects")
    @JoinColumn(name = "id_curso", nullable = false)
    private Course course;

    @Override
    public Subject update(Subject source) {
        if (!source.getName().isBlank()) setName(source.getName());
        if (source.getCourse() != null) setCourse(source.getCourse());
        return source;
    }
}
