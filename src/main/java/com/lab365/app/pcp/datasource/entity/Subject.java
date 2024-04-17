package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "materia")
public class Subject extends GenericEntity<Subject> {
    @Column(name = "nome")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Course course;

    @Override
    public void update(Subject source) {
        if (!source.getName().isBlank()) setName(source.getName());
        if (source.getCourse() != null) setCourse(source.getCourse());
    }
}
