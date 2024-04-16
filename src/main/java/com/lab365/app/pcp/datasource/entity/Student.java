package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "aluno")
@EqualsAndHashCode(callSuper = true)
public class Student extends GenericEntity<Student> {
    @Column(name = "nome", nullable = false)
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "data_nascimento")
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    private Classroom classroom;

    @Override
    public void update(Student source) {
        if (!source.getName().isBlank()) setName(source.getName());
        if (source.getBirthday() != null) setBirthday(source.getBirthday());
        if (source.getClassroom() != null) setClassroom(source.getClassroom());
    }
}
