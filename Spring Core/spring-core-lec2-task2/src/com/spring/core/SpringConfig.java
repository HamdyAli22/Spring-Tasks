package com.spring.core;

import com.spring.core.service.AccountService;
import com.spring.core.service.UserService;
import com.spring.core.service.impl.AccountServiceImpl;
import com.spring.core.service.impl.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.core.service.impl")
public class SpringConfig {

    @Bean(name = "person")
    public UserService personService(){

        return new PersonService();
    }

    @Bean(name = "account")
    public AccountService accountService(){
        return  new AccountServiceImpl(personService());
    }

}
