package com.spring.core;

import com.spring.core.service.UserService;
import com.spring.core.service.impl.PersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAppXml {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
       UserService person1 =  xmlApplicationContext.getBean("personService", PersonService.class);
       person1.save("Hamdy");
        xmlApplicationContext.close();
    }
}
