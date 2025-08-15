package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.model.Employee;
import com.eraasoft.spring.repo.EmployeeRepo;
import com.eraasoft.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

private EmployeeRepo  employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee save(Employee employee) {
        if(Objects.nonNull(employee.getId())){
            throw new RuntimeException("id must be null");
        }
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        boolean hasInvalidId = employees.stream()
                .anyMatch(employee -> Objects.nonNull(employee.getId()));
        if (hasInvalidId) {
            throw new RuntimeException("id must be null for new employee");
        }
        return employeeRepo.saveAll(employees);
    }

    @Override
    public Employee update(Employee employee) {
        if(Objects.isNull(employee.getId())){
            throw new RuntimeException("id must be not null");
        }
        Optional<Employee> optionalEmployee =  employeeRepo.findById(employee.getId());
        if(!optionalEmployee.isPresent()){
            throw new RuntimeException("Employee with id " + employee.getId() + " not found");
        }
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> updateAll(List<Employee> employees) {
        boolean hasNullId = employees.stream()
                .anyMatch(e -> Objects.isNull(e.getId()));
        if (hasNullId) {
            throw new RuntimeException("All employees must have non-null ids when updating");
        }

        employees.stream()
                .forEach(e -> employeeRepo.findById(e.getId())
                        .orElseThrow(() -> new RuntimeException("Employee with id " + e.getId() + " not found")));

        return employeeRepo.saveAll(employees);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public List<Employee> findByIds(List<Long> ids) {
        return employeeRepo.findAllById(ids);
    }

    @Override
    public boolean deleteAll() {
        List<Employee> allEmployees = employeeRepo.findAll();
        if (allEmployees.isEmpty()) {
            return false; // nothing to delete
        }
        employeeRepo.deleteAll();
        return true;
    }

    @Override
    public void deleteById(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));

        employeeRepo.delete(employee);
    }

    @Override
    public void deleteAllByIds(List<Long> ids) {
        List<Employee> existingEmployees = employeeRepo.findAllById(ids);

        List<Long> foundIds = existingEmployees.stream().map(Employee::getId).toList();

        List<Long> notFoundIds = ids.stream().filter(id -> !foundIds.contains(id)).toList();

        if (foundIds.isEmpty()) {
            throw new RuntimeException("No matching employees found for provided IDs: " + ids);
        }

        employeeRepo.deleteAll(existingEmployees);

        if (!notFoundIds.isEmpty()) {
            throw new RuntimeException("Employees with IDs " + notFoundIds + " not found, others deleted");
        }
    }

    @Override
    public List<Employee> findByNameLike(String name) {
        return employeeRepo.findByNameStartingWithIgnoreCase(name);
    }


}
