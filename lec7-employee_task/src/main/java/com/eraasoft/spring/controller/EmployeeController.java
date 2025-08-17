package com.eraasoft.spring.controller;

import com.eraasoft.spring.model.Employee;
import com.eraasoft.spring.service.EmployeeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService  employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("save-employee")
    ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        JSONObject json = new JSONObject();
        try{
            Employee savedEmployee = employeeService.save(employee);
            json.put("status", 1);
            json.put("data", new JSONObject(savedEmployee));
            return ResponseEntity.ok(json.toString());
        } catch (RuntimeException e) {
            json.put("status", 0);
            json.put("message", e.getMessage());
            return ResponseEntity.status(422).body(json.toString());
        }
    }

    @PostMapping("/save-employees")
    ResponseEntity<String> addEmployees(@RequestBody List<Employee> employees) {
        JSONObject response = new JSONObject();
        try{
            List<Employee> savedEmployees = employeeService.saveAll(employees);
            JSONArray dataArray = new JSONArray(savedEmployees);
            response.put("status", 1);
            response.put("data", dataArray);
            return ResponseEntity.ok(response.toString());
        }catch (RuntimeException e) {
            response.put("status", 0);
            response.put("message", e.getMessage());
            return ResponseEntity.status(422).body(response.toString());
        }

    }

    @PostMapping("/update-employee")
    ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        JSONObject json = new JSONObject();
        try {
            Employee updatedEmployee = employeeService.update(employee);
            json.put("status", 1);
            json.put("data", new JSONObject(updatedEmployee));
            return ResponseEntity.ok(json.toString());
        }catch (RuntimeException e) {
            json.put("status", 0);
            json.put("message", e.getMessage());
            return ResponseEntity.status(422).body(json.toString());
        }
    }


    @PutMapping("/update-employees")
    ResponseEntity<String> updateEmployees(@RequestBody List<Employee> employees) {
        JSONObject json = new JSONObject();
        try {
            List<Employee> updatedList = employeeService.updateAll(employees);
            JSONArray dataArray = new JSONArray(updatedList);
            json.put("status", 1);
            json.put("data", dataArray);
            return ResponseEntity.ok(json.toString());
        } catch (RuntimeException e) {
            json.put("status", 0);
            json.put("message", e.getMessage());
            return ResponseEntity.status(422).body(json.toString());
        }
    }

    @GetMapping ("/employees")
    ResponseEntity<String> findAllEmployees() {

        JSONObject json = new JSONObject();
        List<Employee> employees = employeeService.findAll();

        if (employees.isEmpty()) {
            json.put("status", 0);
            json.put("message", "No employees found.");
            return ResponseEntity.status(422).body(json.toString());
        } else {
            json.put("status", 1);
            json.put("data", employees);
            json.put("message", "Employees retrieved successfully.");
            return ResponseEntity.ok(json.toString());
        }
    }

    @GetMapping("/employees/by-ids")
    ResponseEntity<String> getEmployeesByIds(@RequestParam  List<Long> ids) {
        JSONObject json = new JSONObject();
        List<Employee> employees = employeeService.findByIds(ids);
        if (employees.isEmpty()) {
            json.put("status", 0);
            json.put("message", "No employees found for the provided IDs.");
            return ResponseEntity.status(422).body(json.toString());
        }else{
            json.put("status", 1);
            json.put("data", employees);
            json.put("message", "Employees retrieved successfully.");
            return ResponseEntity.ok(json.toString());
        }
    }

    @DeleteMapping("delete-employees")
    ResponseEntity<String> deleteAllEmployees() {
        JSONObject json = new JSONObject();
        boolean deleted = employeeService.deleteAll();

        if (deleted) {
            json.put("status", 1);
            json.put("message", "All employees have been deleted.");
            return ResponseEntity.ok(json.toString());
        } else {
            json.put("status", 0);
            json.put("message", "No employees found to delete.");
            return ResponseEntity.status(422).body(json.toString());
        }
    }

    @DeleteMapping("/delete-employee")
    ResponseEntity<?>  deleteEmployeeById (@RequestParam  Long id){
        JSONObject response = new JSONObject();
        try{
            employeeService.deleteById(id);
            response.put("status", 1);
            response.put("message", "Employee with id " + id + " has been deleted.");
            return ResponseEntity.ok(response.toString());
        }catch (RuntimeException ex) {
            response.put("status", 0);
            response.put("message", ex.getMessage());
            return ResponseEntity.status(422).body(response.toString());
        }
    }

    @DeleteMapping ("/delete-employees/by-ids")
     ResponseEntity<String> deleteEmployeesByIds(@RequestParam List<Long> ids){
        JSONObject json = new JSONObject();
        try{
            employeeService.deleteAllByIds(ids);
            json.put("status", 1);
            json.put("message", "Requested employees processed successfully.");
            return ResponseEntity.ok(json.toString());
        }catch (RuntimeException e) {
            json.put("status", 0);
            json.put("message", e.getMessage());
            return ResponseEntity.status(422).body(json.toString());
        }
    }

    @GetMapping("/employees/search_by_name")
   ResponseEntity<String> searchEmployeesByName(@RequestParam String name){
        JSONObject json = new JSONObject();
        List<Employee> employees = employeeService.findByNameLike(name);
        if (employees.isEmpty()){
            json.put("status", 0);
            json.put("message", "No employees found with name starting with: " + name);
            return ResponseEntity.status(422).body(json.toString());
        }else{
            json.put("status", 1);
            json.put("data", employees);
            json.put("message", "Employees retrieved successfully.");
            return ResponseEntity.ok(json.toString());
        }
   }

}
