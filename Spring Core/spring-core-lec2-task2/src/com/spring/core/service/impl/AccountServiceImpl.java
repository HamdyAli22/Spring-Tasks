package com.spring.core.service.impl;

import com.spring.core.service.AccountService;
import com.spring.core.service.UserService;

public class AccountServiceImpl implements AccountService {

    private UserService personService;

    public AccountServiceImpl(UserService personService) {
        this.personService = personService;
    }

    @Override
    public void getSavePerson(String name) {
        System.out.println("AccountServiceImpl calling PersonService...");
        personService.save(name);

    }
}
