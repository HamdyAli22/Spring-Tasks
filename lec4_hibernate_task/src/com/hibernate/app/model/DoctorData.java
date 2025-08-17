package com.hibernate.app.model;

import javax.persistence.*;

@Entity
@Table(name = "doctor_data")
public class DoctorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "first_name")
    private  String firstName;

    @Column(name = "last_name")
    private String  lastName;


    private  Integer age;

    @OneToOne
    @JoinColumn(name = "doctor_id",unique = true)
    private Doctor doctor;

    public DoctorData() {
    }

    public DoctorData(String fukkAddress, String firstName, String lastName, Integer age) {
        this.fullAddress = fukkAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
