package com.hibernate.app;

import com.hibernate.app.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Teacher.class);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction =  session.beginTransaction();

        try{
            Teacher teacher = new Teacher("Ali", 18, "cairo");
            session.save(teacher);
            transaction.commit();

            System.out.println("Saved teacher with ID: " + teacher.getId());
        }finally {
            factory.close();
            session.close();
        }
    }
}
