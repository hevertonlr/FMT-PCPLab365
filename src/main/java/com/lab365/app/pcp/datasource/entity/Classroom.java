package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "turma")
public class Classroom extends GenericEntity<Classroom> {
    private String name;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Course course;

    @Override
    public void update(Classroom source) {
        if (!source.getName().isBlank()) setName(source.getName());
        if (source.getTeacher() != null) setTeacher(source.getTeacher());
        if (source.getCourse() != null) setCourse(source.getCourse());
    }
}
