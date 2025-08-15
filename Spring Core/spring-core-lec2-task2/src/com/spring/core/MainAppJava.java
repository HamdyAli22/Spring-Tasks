package com.spring.core;


import com.spring.core.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAppJava {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService =  applicationContext.getBean("account",   AccountService.class);
        accountService.getSavePerson("Hamdy");
    }
}