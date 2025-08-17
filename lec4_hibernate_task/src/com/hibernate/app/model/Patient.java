package com.hibernate.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "type_of_disease")
    private String typeOfDisease;

    public Patient() {
    }

    public Patient(Integer id, String name, String typeOfDisease) {
        this.id = id;
        this.name = name;
        this.typeOfDisease = typeOfDisease;
    }

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToMany(mappedBy = "patients")
    private Set<Hospital> hospitals = new HashSet<Hospital>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfDisease() {
        return typeOfDisease;
    }

    public void setTypeOfDisease(String typeOfDisease) {
        this.typeOfDisease = typeOfDisease;
    }
}
