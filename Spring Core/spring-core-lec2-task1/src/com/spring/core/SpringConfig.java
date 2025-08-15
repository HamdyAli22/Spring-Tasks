package com.spring.core;

import com.spring.core.service.UserService;
import com.spring.core.service.impl.ManagerService;
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

    @Bean(name = "manager")
    public UserService managerService() {
        return new ManagerService();
    }
}
