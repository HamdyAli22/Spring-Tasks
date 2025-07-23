package com.unicourse.spring.controller;

import com.unicourse.spring.model.Course;
import com.unicourse.spring.model.Instructor;
import com.unicourse.spring.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController {
    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/save-instructors")
    ResponseEntity<List<Instructor>> saveInstructors(@RequestBody List<Instructor> instructors) {
        return ResponseEntity.ok(instructorService.saveAll(instructors));
    }

    @GetMapping("/instructors")
    ResponseEntity<Object> getInstructors(@RequestParam(required = false) Long id){
        if(id != null) {
            return ResponseEntity.ok(instructorService.getInstructorById(id));
        }
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @DeleteMapping("delete-instructor")
    ResponseEntity<String> deleteCourse(@RequestParam(required = false) Long id) {
        if (id != null) {
            return instructorService.deleteInstructorById(id)? ResponseEntity.ok("Instructor with ID " + id + " has been deleted.") : ResponseEntity.status(404).body("Instructor not found with ID: " + id);
        }
        return instructorService.deleteAllInstructors() ? ResponseEntity.ok("All instructors have been deleted.") : ResponseEntity.status(204).body("No instructors found to delete.");
    }
}
