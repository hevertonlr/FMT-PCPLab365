package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab365.app.pcp.datasource.enums.CivilStateEnum;

import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity()
@DynamicUpdate
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Teacher.withAddressAndUser", attributeNodes = {
                @NamedAttributeNode("address"),
                @NamedAttributeNode("user")
        })
})
@Table()
public class Teacher extends Person<Teacher> {

    @Enumerated(EnumType.STRING)
    @Column()
    private CivilStateEnum civilState;

    @Column()
    private String nationality;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_subjects", joinColumns = @JoinColumn(name = "id_teacher"), inverseJoinColumns = @JoinColumn(name = "id_subject"))
    private Set<Subject> subjects;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User user;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Set<Classroom> classrooms;

    @Override
    public void update(Teacher source) {
        super.update(source);
        if (source.getCivilState() != null)
            this.setCivilState(source.getCivilState());
        if (!source.getNationality().isBlank())
            this.setNationality(source.getNationality());
        if (source.getSubjects() != null)
            this.setSubjects(source.getSubjects());
        if (source.getUser() != null)
            this.setUser(source.getUser());
    }
}
