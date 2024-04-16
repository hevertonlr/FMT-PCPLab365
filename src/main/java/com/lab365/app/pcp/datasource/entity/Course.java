package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "curso")
@EqualsAndHashCode(callSuper = true)
public class Course extends GenericEntity<Course> {

    @Column(name = "nome", nullable = false)
    private String name;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Subject> subjects;

    @Override
    public void update(Course source) {
        if (!source.getName().isBlank()) setName(source.getName());
    }
}
