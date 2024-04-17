package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "turma")
public class Classroom extends GenericEntity<Classroom> {
    private String name;

    @JsonIgnoreProperties("classroms")
    @OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToOne
    @JsonIgnoreProperties("classroms")
    @JoinColumn(name = "id_professor", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JsonIgnoreProperties("classroms")
    @JoinColumn(name = "id_curso", nullable = false)
    private Course course;

    @Override
    public Classroom update(Classroom source) {
        if (!source.getName().isBlank()) setName(source.getName());
        if (source.getTeacher() != null) setTeacher(source.getTeacher());
        if (source.getCourse() != null) setCourse(source.getCourse());
        return source;
    }
}
