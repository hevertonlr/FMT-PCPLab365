package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name = "papel", indexes = @Index(columnList = "nome"))
@EqualsAndHashCode(callSuper = true)
public class Role extends GenericEntity<Role> implements GrantedAuthority {
    @Column(name = "nome", nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public void update(Role source) {
        this.name = !source.getName().isBlank() ? source.getName() : this.name;
    }
}
