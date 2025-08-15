package com.unicourse.spring.controller;

import com.unicourse.spring.model.Course;
import com.unicourse.spring.model.Student;
import com.unicourse.spring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/save-courses")
    ResponseEntity<List<Course>> saveCourses(@RequestBody List<Course> courses) {
        return ResponseEntity.ok(courseService.saveAll(courses));
    }

    @PutMapping("/assign-instructor")
    public ResponseEntity<Course> assignInstructorToCourse(@RequestParam Long courseId,@RequestParam Long instructorId) {
        return ResponseEntity.ok(courseService.assignInstructorToCourse(courseId, instructorId));
    }

    @GetMapping("/courses")
    ResponseEntity<Object> getCourses(@RequestParam(required = false) Long id){
        if(id != null) {
            return ResponseEntity.ok(courseService.getCourseById(id));
        }
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @DeleteMapping("delete-course")
    ResponseEntity<String> deleteCourse(@RequestParam(required = false) Long id) {
        if (id != null) {
            return courseService.deleteCourseById(id) ? ResponseEntity.ok("Course with ID " + id + " has been deleted.") : ResponseEntity.status(404).body("Course not found with ID: " + id);
        }
        return courseService.deleteAllCourses() ? ResponseEntity.ok("All courses have been deleted.") : ResponseEntity.status(204).body("No courses found to delete.");
    }

}
