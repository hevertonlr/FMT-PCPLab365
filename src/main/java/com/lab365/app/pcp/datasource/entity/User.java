package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "usuario")
@EqualsAndHashCode(callSuper = true)
public class User extends GenericEntity<User>{
    @Column(name = "nome_usuario",nullable = false)
    private String username;
    @Column(name = "senha",nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name="id_papel")
    private Role role;
}
