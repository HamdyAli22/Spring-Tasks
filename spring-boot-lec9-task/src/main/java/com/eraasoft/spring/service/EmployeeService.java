package com.eraasoft.spring.service;

import com.eraasoft.spring.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto save(EmployeeDto employeeDto);
    EmployeeDto update(EmployeeDto employeeDto);
    void delete(Long id);
    List<EmployeeDto> findAll();
    EmployeeDto findById(Long id);
    List<EmployeeDto> findByIds(List<Long> ids);
    List<EmployeeDto> findByNames(List<String> names);
}
