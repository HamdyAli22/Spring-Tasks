package com.eraasoft.spring.controller;


import com.eraasoft.spring.dto.StudentDto;
import com.eraasoft.spring.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService  studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save-student")
    ResponseEntity<StudentDto> addStudent(@RequestBody @Valid StudentDto student){
        return ResponseEntity.ok( studentService.save(student));
    }

    @PutMapping("/update-student")
    ResponseEntity<StudentDto> updateStudent(@RequestBody @Valid StudentDto student){

        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("/delete-student/{student_id}")
    ResponseEntity<Void> deleteStudent(@PathVariable("student_id") Long id){
       return studentService.delete(id)? ResponseEntity.noContent().build():ResponseEntity.notFound().build();

    }

    @GetMapping("/students")
    ResponseEntity<List<StudentDto>> findAllStudents(){
       return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/get-student")
    ResponseEntity<StudentDto> findStudent(@RequestParam("student_id") Long id){
       return   ResponseEntity.ok(studentService.findById(id));
    }

    @GetMapping("/get-by-username")
    ResponseEntity<List<StudentDto>> findStudentByUsername(@RequestParam("username") String username){
        return ResponseEntity.ok(studentService.findByUsername(username));
    }



}
