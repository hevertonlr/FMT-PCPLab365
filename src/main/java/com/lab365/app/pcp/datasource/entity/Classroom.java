package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity()
@DynamicUpdate
@Table()
public class Classroom extends GenericEntity<Classroom> {
    private String name;

    @JsonIgnore
    @JsonIgnoreProperties({ "classroom", "user" })
    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Set<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    // @JsonIgnoreProperties({"classrooms", "user", "teachers"})
    @JoinTable(name = "teacher_classroom", joinColumns = @JoinColumn(name = "id_classroom"), inverseJoinColumns = @JoinColumn(name = "id_teacher"))
    private Set<Teacher> teachers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    // @JsonIgnoreProperties({"classrooms", "students", "user"})
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    // @JsonIgnoreProperties({"course", "classrooms", "subjects"})
    @JoinTable(name = "subject_classroom", joinColumns = @JoinColumn(name = "id_classroom"), inverseJoinColumns = @JoinColumn(name = "id_subject"))
    private Set<Subject> subjects;

    @Override
    public void update(Classroom source) {
        if (!source.getName().isBlank())
            this.setName(source.getName());
        if (source.getTeachers() != null)
            this.setTeachers(source.getTeachers());
        if (source.getCourse() != null)
            this.setCourse(source.getCourse());
    }
}
