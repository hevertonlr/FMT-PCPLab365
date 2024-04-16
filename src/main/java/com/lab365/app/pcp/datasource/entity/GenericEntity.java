package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
@DynamicUpdate
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class GenericEntity<T> extends Auditable<String> implements Serializable, IGenericEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
