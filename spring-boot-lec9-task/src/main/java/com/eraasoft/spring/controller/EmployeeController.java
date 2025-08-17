package com.eraasoft.spring.controller;

import com.eraasoft.spring.dto.EmployeeDto;
import com.eraasoft.spring.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save-employee")
    ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employee){
        return ResponseEntity.ok( employeeService.save(employee));
    }

    @PostMapping("/update-employee")
    ResponseEntity<EmployeeDto> updateEmployee(@RequestBody @Valid EmployeeDto employee){
        return ResponseEntity.ok( employeeService.update(employee));
    }

    @DeleteMapping("/delete-employee")
    ResponseEntity<Void> removeEmployee(@RequestParam Long id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(employeeService.findById(id));
        }
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/employees/by-ids")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByIds (@RequestParam List<Long> ids){
        return ResponseEntity.ok(employeeService.findByIds(ids));
    }

    @GetMapping("/employees/by-names")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByNames (@RequestParam List<String> names){
        return ResponseEntity.ok(employeeService.findByNames(names));
    }

}
