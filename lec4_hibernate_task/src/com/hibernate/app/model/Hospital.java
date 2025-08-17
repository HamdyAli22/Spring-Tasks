package com.hibernate.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name="number_of_doctors")
    private Integer numberOfDoctors;

    @Column(name="number_of_patient")
    private Integer numberOfPatient;

    @OneToMany(mappedBy = "hospital")
    private Set<Doctor> doctors = new HashSet<Doctor>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hospital_patient",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<Patient>  patients = new HashSet<Patient>();

    public Hospital() {
    }

    public Hospital(String name, Integer numberOfDoctors, Integer numberOfPatient) {
        this.name = name;
        this.numberOfDoctors = numberOfDoctors;
        this.numberOfPatient = numberOfPatient;
    }

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

    public Integer getNumberOfDoctors() {
        return numberOfDoctors;
    }

    public void setNumberOfDoctors(Integer numberOfDoctors) {
        this.numberOfDoctors = numberOfDoctors;
    }

    public Integer getNumberOfPatient() {
        return numberOfPatient;
    }

    public void setNumberOfPatient(Integer numberOfPatient) {
        this.numberOfPatient = numberOfPatient;
    }
}
