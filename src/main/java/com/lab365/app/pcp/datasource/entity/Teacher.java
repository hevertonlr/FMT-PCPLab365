package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Data
@Entity
@DynamicUpdate
@Table(name = "docente")
@EqualsAndHashCode(callSuper = true)
public class Teacher extends GenericEntity<Teacher> {

    @Column(name = "nome", nullable = false)
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "data_entrada")
    private LocalDate entryDate;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @Override
    public void update(Teacher source) {
        this.name = !source.getName().isBlank() ? source.getName() : this.name;
        this.entryDate = source.getEntryDate() != null ? source.getEntryDate() : this.getEntryDate();
    }
}
