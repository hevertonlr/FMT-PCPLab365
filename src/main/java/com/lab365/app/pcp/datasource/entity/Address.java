package com.lab365.app.pcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity()
@DynamicUpdate
@Table()
public class Address extends GenericEntity<Address>{
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String city;
    @Column( nullable = false)
    private String state;
    @Column(nullable = false)
    private String street;
    @Column( nullable = false)
    private String number;
    @Column()
    private String complement;
    @Column( nullable = false)
    private String neighborhood;
    @Column()
    private String referencePoint;
//    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
//    private Teacher teacher;
//    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
//    private Student student;

    @Override
    public void update(Address source) {
        if (!source.getCep().isBlank()) this.setCep(source.getCep());
        if (!source.getCity().isBlank()) this.setCity(source.getCity());
        if (!source.getState().isBlank()) this.setState(source.getState());
        if (!source.getStreet().isBlank()) this.setStreet(source.getStreet());
        if (!source.getNumber().isBlank()) this.setNumber(source.getNumber());
        if (!source.getComplement().isBlank()) this.setComplement(source.getComplement());
        if (!source.getNeighborhood().isBlank()) this.setNeighborhood(source.getNeighborhood());
        if (!source.getReferencePoint().isBlank()) this.setReferencePoint(source.getReferencePoint());
    }
}