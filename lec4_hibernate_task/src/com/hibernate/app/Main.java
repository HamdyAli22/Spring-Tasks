package com.hibernate.app;


import com.hibernate.app.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Book.class);
        SessionFactory factory = configuration.buildSessionFactory();
    }
}
