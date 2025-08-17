package com.spring.core.service.impl;

import com.spring.core.service.UserService;

public class PersonService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("PersonService Save: " + name);
    }

    @Override
    public void update(String name) {
        System.out.println("PersonService Update: " + name);
    }
}
