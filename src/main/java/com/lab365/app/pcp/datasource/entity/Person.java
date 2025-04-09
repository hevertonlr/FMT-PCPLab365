package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Data
@DynamicUpdate
@MappedSuperclass
public abstract class Person<T> extends GenericEntity<T> {

    @Column( nullable = false)
    private String name;

    @Column(nullable = false)
    private GenderEnum gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column()
    private LocalDate birthday;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String phone;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Address address;

    public void update(Person<T> source) {
        if (!source.getName().isBlank()) this.setName(source.getName());
        if (source.getGender() != null) this.setGender(source.getGender());
        if (source.getBirthday() != null) this.setBirthday(source.getBirthday());
        if (!source.getCpf().isBlank()) this.setCpf(source.getCpf());
        if (!source.getRg().isBlank()) this.setRg(source.getRg());
        if (!source.getPhone().isBlank()) this.setPhone(source.getPhone());
        if (source.getAddress() != null) this.setAddress(source.getAddress());
    }
}
