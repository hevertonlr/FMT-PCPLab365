package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "Docente")
@DynamicUpdate
@Table(name = "docente")
public class Teacher extends GenericEntity<Teacher> {

    @Column(name = "nome", nullable = false)
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "data_entrada")
    private LocalDate entryDate;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private User user;

    @Override
    public void update(Teacher source) {
        if (!source.getName().isBlank()) this.setName(source.getName());
        if (source.getEntryDate() != null) this.setEntryDate(source.getEntryDate());
    }

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Classroom> classrooms;
}
