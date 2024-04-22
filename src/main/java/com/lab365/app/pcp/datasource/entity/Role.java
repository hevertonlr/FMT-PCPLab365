package com.lab365.app.pcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity(name = "Papel")
@DynamicUpdate
@Table(name = "papel", indexes = @Index(columnList = "nome"))
public class Role extends GenericEntity<Role> implements GrantedAuthority {
    @Column(name = "nome", nullable = false)
    private String name;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.name;
    }

    @Override
    public void update(Role source) {
        if (!source.getName().isBlank()) this.setName(source.getName());
    }
}
