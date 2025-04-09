package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity()
@DynamicUpdate
@Table()
public class Student extends Person<Student> {

    @Column()
    private String placeofbirth;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("role")
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties({"students", "teachers", "subjects", "course"})
    @JoinColumn(name = "id_classroom", nullable = false)
    private Classroom classroom;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Grade> grades;

    public void update(Student source) {
        super.update(source);
        if (source.getUser() != null) this.setUser(source.getUser());
        if (source.getClassroom() != null) this.setClassroom(source.getClassroom());
        if (source.getGrades() != null) this.setGrades(source.getGrades());
    }


}
