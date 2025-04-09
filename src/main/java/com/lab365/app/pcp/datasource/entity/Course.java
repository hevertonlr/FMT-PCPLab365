package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity()
@DynamicUpdate
@Table()
public class Course extends GenericEntity<Course> {

    @Column(nullable = false)
    private String name;

    @ToString.Exclude
    @JsonIgnore
    // @JsonIgnoreProperties({"course", "students", "teacher"})
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Classroom> classrooms;

    @ToString.Exclude
    @JsonIgnore
    // @JsonIgnoreProperties({"course", "user"})
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Subject> subjects;

    @Override
    public void update(Course source) {
        if (!source.getName().isBlank())
            this.setName(source.getName());
    }
}
