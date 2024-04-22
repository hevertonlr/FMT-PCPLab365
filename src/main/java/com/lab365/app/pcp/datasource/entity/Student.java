package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "Aluno")
@DynamicUpdate
@Table(name = "aluno")
public class Student extends GenericEntity<Student> {
    @Column(name = "nome", nullable = false)
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "data_nascimento")
    private LocalDate birthday;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("role")
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties({"students", "teacher", "course"})
    @JoinColumn(name = "id_turma", nullable = false)
    private Classroom classroom;

    @Override
    public void update(Student source) {
        if (!source.getName().isBlank()) this.setName(source.getName());
        if (source.getBirthday() != null) this.setBirthday(source.getBirthday());
        if (source.getClassroom() != null) this.setClassroom(source.getClassroom());
    }

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Grade> grades;
}
