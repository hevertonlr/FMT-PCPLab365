package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity(name = "Turma")
@DynamicUpdate
@Table(name = "turma")
public class Classroom extends GenericEntity<Classroom> {
    private String name;

    @JsonIgnoreProperties("classroom")
    @OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JsonIgnoreProperties({"classrooms", "students", "user"})
    @JoinColumn(name = "id_curso", nullable = false)
    private Course course;

    @Override
    public void update(Classroom source) {
        if (!source.getName().isBlank()) this.setName(source.getName());
        if (source.getTeacher() != null) this.setTeacher(source.getTeacher());
        if (source.getCourse() != null) this.setCourse(source.getCourse());
    }
}
