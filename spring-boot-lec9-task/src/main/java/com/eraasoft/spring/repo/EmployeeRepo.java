package com.eraasoft.spring.repo;


import com.eraasoft.spring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    boolean existsByName(String name);

    @Query("SELECT e from Employee e where lower(e.name) IN :names ")
    List<Employee> findByNameInIgnoreCase(@Param("names") List<String> names);
}
