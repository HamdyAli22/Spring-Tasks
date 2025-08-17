package com.hibernate.app;

import com.hibernate.app.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DoctorMain {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Doctor.class)
                .addAnnotatedClass(DoctorData.class)
                .addAnnotatedClass(Hospital.class)
                .addAnnotatedClass(Patient.class);
        SessionFactory factory = configuration.buildSessionFactory();


    }
}
