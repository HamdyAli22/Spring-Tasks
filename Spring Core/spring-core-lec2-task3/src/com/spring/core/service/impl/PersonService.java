package com.spring.core.service.impl;

import com.spring.core.service.UserService;

public class PersonService implements UserService {

    public PersonService() {
    }

    @Override
    public void save(String name) {
        System.out.println("PersonService Save: " + name);
    }

    public void init() {
        System.out.println("PersonService Init Method Called");
    }

    public void destroy() {
        System.out.println("PersonService Destroy Method Called");
    }
}
