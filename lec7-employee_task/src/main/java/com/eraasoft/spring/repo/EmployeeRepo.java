package com.eraasoft.spring.repo;

import com.eraasoft.spring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    List<Employee> findByNameStartingWithIgnoreCase(String prefix);
}
