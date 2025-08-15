package com.spring.core;

import com.spring.core.service.impl.ManagerService;
import com.spring.core.service.impl.PersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAppXml {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext xmlApplicationContext =
               new ClassPathXmlApplicationContext("applicationContext.xml");

        PersonService personService = xmlApplicationContext.getBean("personService",PersonService.class);
        ManagerService managerService = xmlApplicationContext.getBean("managerService",ManagerService.class);

        personService.save("Hamdy");
        personService.update("Hamdy");


        managerService.save("Ali");
        managerService.update("Ali");




    }
}
