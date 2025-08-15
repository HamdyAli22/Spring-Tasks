package com.eraasoft.spring.service;

import com.eraasoft.spring.model.Employee;

import java.util.List;

public interface EmployeeService {

          Employee save(Employee employee);
          List<Employee> saveAll(List<Employee> employees);
          Employee update(Employee employee);
          List<Employee> updateAll(List<Employee> employees);
          List<Employee> findAll ();
          List<Employee> findByIds(List<Long> ids);
          boolean deleteAll();
          void deleteById(Long id);
          void deleteAllByIds(List<Long> ids);
          public List<Employee> findByNameLike(String name);
}
