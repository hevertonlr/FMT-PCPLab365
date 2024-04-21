package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity(name = "Matéria")
@DynamicUpdate
@Table(name = "materia")
public class Subject extends GenericEntity<Subject> {
    @Column(name = "nome")
    private String name;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties({"subjects", "students", "teacher"})
    @JoinColumn(name = "id_curso", nullable = false)
    private Course course;

    @Override
    public void update(Subject source) {
        if (!source.getName().isBlank()) this.setName(source.getName());
        if (source.getCourse() != null) this.setCourse(source.getCourse());
    }
}
