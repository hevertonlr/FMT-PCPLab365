package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Student extends Person<Student> {

    @Column()
    private String placeofbirth;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("role")
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User user;

    @ManyToOne()
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "createdBy", "lastModifyBy", "name", "students",
            "teachers",
            "subjects", "course" })
    @JoinColumn(name = "id_classroom", nullable = false)
    private Classroom classroom;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private Set<Grade> grades;

    public void update(Student source) {
        super.update(source);
        if (source.getPlaceofbirth() != null)
            this.setPlaceofbirth(source.getPlaceofbirth());
        if (source.getUser() != null)
            this.setUser(source.getUser());
        if (source.getClassroom() != null)
            this.setClassroom(source.getClassroom());
        if (source.getGrades() != null)
            this.setGrades(source.getGrades());
    }

}
