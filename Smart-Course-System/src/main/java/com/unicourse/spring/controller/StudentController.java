package com.unicourse.spring.controller;


import com.unicourse.spring.model.Student;
import com.unicourse.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService  studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save-students")
    ResponseEntity<List<Student>> saveStudents(@RequestBody List<Student>  students) {
      return ResponseEntity.ok(studentService.saveAll(students));
    }

    @PutMapping("/register-student-to-course")
    ResponseEntity<Student> registerStudentToCourse(@RequestParam Long studentId,@RequestParam Long courseId) {
        return ResponseEntity.ok(studentService.registerStudentToCourse(studentId, courseId));
    }

    @GetMapping("/students")
    ResponseEntity<Object> getStudents(@RequestParam(required = false) Long id){
        if(id != null) {
            return ResponseEntity.ok(studentService.getStudentById(id));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @DeleteMapping("delete-student")
    ResponseEntity<String> deleteStudent(@RequestParam(required = false) Long id) {
        if (id != null) {
            return studentService.deleteStudentById(id) ? ResponseEntity.ok("Student with ID " + id + " has been deleted.") : ResponseEntity.status(404).body("Student not found with ID: " + id);
        }
        return studentService.deleteAllStudents() ? ResponseEntity.ok("All students have been deleted.") : ResponseEntity.status(204).body("No students found to delete.");
    }
}
