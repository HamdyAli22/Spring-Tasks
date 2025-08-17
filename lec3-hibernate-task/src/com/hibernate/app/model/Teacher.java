package com.hibernate.app.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq_gen")
    @SequenceGenerator(name = "teacher_seq_gen", sequenceName = "teacher_seq", allocationSize = 1)
    @Column(name = "teacher_id")
     private Integer id;

    @Column(name = "teacher_name",length = 50)
     private String Name;

    @Min(15)
    @Max(20)
     private Integer age;

    @Column(unique = true, nullable = false)
     private String address;

    public Teacher() {
    }

    public Teacher(String name, Integer age, String address) {
        Name = name;
        this.age = age;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
