package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Data
@Entity
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

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private User user;

    @Override
    public void update(Teacher source) {
        if (!source.getName().isBlank()) setName(source.getName());
        if (source.getEntryDate() != null) setEntryDate(source.getEntryDate());
    }
}
