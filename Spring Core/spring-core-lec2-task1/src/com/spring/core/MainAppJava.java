package com.spring.core;

import com.spring.core.service.impl.ManagerService;
import com.spring.core.service.impl.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAppJava {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        PersonService  personService =  applicationContext.getBean("person",   PersonService.class);
        ManagerService managerService =  applicationContext.getBean("manager", ManagerService.class);

        personService.save("Hamdy");
        personService.update("Hamdy");

        managerService.save("Ali");
        managerService.update("Ali");
    }
}