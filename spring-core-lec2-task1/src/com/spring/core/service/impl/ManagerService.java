package com.spring.core.service.impl;

import com.spring.core.service.UserService;

public class ManagerService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("ManagerService Save: " + name);
    }

    @Override
    public void update(String name) {
        System.out.println("ManagerService Update: " + name);
    }
}
