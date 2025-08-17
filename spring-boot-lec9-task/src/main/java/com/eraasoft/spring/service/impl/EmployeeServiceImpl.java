package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.dto.EmployeeDto;
import com.eraasoft.spring.mapper.EmployeeMapper;
import com.eraasoft.spring.model.Employee;
import com.eraasoft.spring.repo.EmployeeRepo;
import com.eraasoft.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;
    private EmployeeMapper  employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {

        if(Objects.nonNull(employeeDto.getId())){
            throw new RuntimeException("employee.id.null");
        }

        if(employeeRepo.existsByName(employeeDto.getName())){
            throw new RuntimeException("employee.name.exists");
        }

        Employee employee = employeeMapper.toEmployee(employeeDto);

        employee = employeeRepo.save(employee);
        EmployeeDto savedEmployee =  employeeMapper.toEmployeeDto(employee);
        savedEmployee.setId(employee.getId());
        return savedEmployee;
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        if(Objects.isNull(employeeDto.getId())){
            throw new RuntimeException("employee.id.required");
        }

         employeeRepo.findById(employeeDto.getId())
                .orElseThrow(() -> new RuntimeException("employee.not.found"));

        Employee employee = employeeMapper.toEmployee(employeeDto);
        employeeRepo.save(employee);

        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("employee.not.found"));
        employeeRepo.delete(employee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepo.findAll();
        return employeeMapper.toEmployeeDtoList(employees);
    }

    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("employee.not.found"));
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> findByIds(List<Long> ids) {
        List<Employee> employees = employeeRepo.findAllById(ids);
        if(employees.isEmpty()){
            throw new RuntimeException("employee.not.found");
        }
        return employeeMapper.toEmployeeDtoList(employees);
    }

    @Override
    public List<EmployeeDto> findByNames(List<String> names) {
        List<String> lowerCaseNames = names.stream().map(String::toLowerCase).toList();
        List<Employee> employees = employeeRepo.findByNameInIgnoreCase(lowerCaseNames);
        if (employees.isEmpty()) {
            throw new RuntimeException("employee.not.found");
        }
        return employeeMapper.toEmployeeDtoList(employees);
    }


}
