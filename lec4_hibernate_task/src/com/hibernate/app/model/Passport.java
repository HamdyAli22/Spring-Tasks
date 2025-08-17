package com.hibernate.app.model;

import javax.persistence.*;

@Entity
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private Integer id;

    @Column(name = "passport_number")
    private Integer passportNumber;

    @OneToOne(mappedBy = "passport")
    private Student student;

    public Passport() {
    }

    public Passport(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }
}
