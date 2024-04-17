package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "curso")
public class Course extends GenericEntity<Course> {

    @Column(name = "nome", nullable = false)
    private String name;

    @JsonIgnoreProperties("course")
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Classroom> classrooms;

    @JsonIgnoreProperties("course")
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Subject> subjects;

    @Override
    public Course update(Course source) {
        if (!source.getName().isBlank()) setName(source.getName());
        return source;
    }
}
