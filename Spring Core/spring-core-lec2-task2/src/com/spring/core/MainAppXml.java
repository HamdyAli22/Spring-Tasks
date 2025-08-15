package com.spring.core;

import com.spring.core.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAppXml {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext xmlApplicationContext =
               new ClassPathXmlApplicationContext("applicationContext.xml");

        AccountService accountService = xmlApplicationContext.getBean("accountService", AccountService.class);
        accountService.getSavePerson("Hamdy");




    }
}
