package com.spring.core;

import com.spring.core.service.UserService;
import com.spring.core.service.impl.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.spring.core.service.impl")
public class SpringConfig {

    @Bean(name = "person", initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    public PersonService personService() {
        return new PersonService();
    }
}
