package com.unicourse.spring.service.impl;

import com.unicourse.spring.model.Course;
import com.unicourse.spring.model.Instructor;
import com.unicourse.spring.repo.CourseRepo;
import com.unicourse.spring.repo.InstructorRepo;
import com.unicourse.spring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

   private CourseRepo courseRepo;

    @Autowired
    private InstructorRepo instructorRepo;

   @Autowired
    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Course> saveAll(List<Course> courses) {

        boolean hasInvalidId = courses.stream().anyMatch(course -> Objects.nonNull(course.getId()));
        if (hasInvalidId) {
            throw new RuntimeException("id must be null for new course");
        }
        return courseRepo.saveAll(courses);
    }

    @Override
    public Course assignInstructorToCourse(Long courseId, Long instructorId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + instructorId));

        course.setInstructor(instructor);
        return courseRepo.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Boolean deleteCourseById(Long id) {
        if(courseRepo.existsById(id)) {
            courseRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAllCourses() {
       List<Course> allCourses = courseRepo.findAll();
       if(!allCourses.isEmpty()) {
           courseRepo.deleteAll();
           return true;
       }
        return false;
    }
}
