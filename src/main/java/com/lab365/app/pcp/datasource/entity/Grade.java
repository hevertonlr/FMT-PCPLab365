package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity()
@DynamicUpdate
@Table()
public class Grade extends GenericEntity<Grade> {
    @ColumnDefault(value = "0.00")
    @Column( precision = 5, scale = 2)
    private BigDecimal value;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column()
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"grades", "user", "classroom", "id"})
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"grades", "user", "entryDate", "id"})
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"grades", "course"})
    @JoinColumn(name = "id_subject", nullable = false)
    private Subject subject;

    @Override
    public void update(Grade source) {
        if (source.getValue() != null && !source.getValue().equals(BigDecimal.ZERO)) this.setValue(source.getValue());
        if (source.getDate() != null) this.setDate(source.getDate());
        if (source.getStudent() != null) this.setStudent(source.getStudent());
        if (source.getTeacher() != null) this.setTeacher(source.getTeacher());
        if (source.getSubject() != null) this.setSubject(source.getSubject());
    }
}
