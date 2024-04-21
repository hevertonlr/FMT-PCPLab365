package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity(name = "Curso")
@DynamicUpdate
@Table(name = "curso")
public class Course extends GenericEntity<Course> {

    @Column(name = "nome", nullable = false)
    private String name;

    @ToString.Exclude
    @JsonIgnoreProperties("course")
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Classroom> classrooms;

    @ToString.Exclude
    @JsonIgnoreProperties({"course", "user"})
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Subject> subjects;

    @Override
    public void update(Course source) {
        if (!source.getName().isBlank()) this.setName(source.getName());
    }
}
