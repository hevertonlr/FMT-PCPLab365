package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Entity(name = "Usuário")
@DynamicUpdate
@Table(name = "usuario")
public class User extends GenericEntity<User> {

    @Column(name = "nome_usuario", nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "senha", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_papel", nullable = false)
    private Role role;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public void update(User source) {
        if (!source.getPassword().isBlank()) this.setPassword(source.getPassword());
        if (source.getRole() != null) this.setRole(source.getRole());
    }
}
