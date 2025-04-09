package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity()
@DynamicUpdate
@Table()
public class Subject extends GenericEntity<Subject> {
    @Column()
    private String name;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties({"subjects", "classrooms"})
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    @JsonIgnore
//    @JsonIgnoreProperties({"subjects", "teachers", "students", "course"})
    private List<Classroom> classrooms;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
//    @JsonIgnoreProperties({"subjects", "classrooms", "course"})
    @JoinTable(name = "teacher_subjects",
            joinColumns = @JoinColumn(name = "id_subject"),
            inverseJoinColumns = @JoinColumn(name = "id_teacher")
    )
    private List<Teacher> teachers;

    @Override
    public void update(Subject source) {
        if (!source.getName().isBlank()) this.setName(source.getName());
        if (source.getCourse() != null) this.setCourse(source.getCourse());
    }
}
