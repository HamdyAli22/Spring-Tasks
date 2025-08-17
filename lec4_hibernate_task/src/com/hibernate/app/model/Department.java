package com.hibernate.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="department_id")
    private Integer id;

    @Column(name = "department_name" ,nullable = false)
    private String name;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<Employee>();


    public Department() {
    }

    public Department(String name) {
        this.name = name;
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
}
