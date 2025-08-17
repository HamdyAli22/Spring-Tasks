package com.eraasoft.spring.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private Double salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Email> emails;

    public void addEmail(Email email) {
        emails.add(email);
        email.setEmployee(this);
    }

    public void setEmails(List<Email> emails) {
        if (this.emails == null) {
            this.emails = new ArrayList<>();
        } else {
            this.emails.clear();
        }

        if (emails != null) {
            emails.forEach(email -> email.setEmployee(this)); // set back-reference
            this.emails.addAll(emails);
        }
    }
}