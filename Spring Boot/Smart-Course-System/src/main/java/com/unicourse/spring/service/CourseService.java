package com.unicourse.spring.service;

import com.unicourse.spring.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> saveAll(List<Course> courses);
    Course assignInstructorToCourse(Long courseId, Long instructorId);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    Boolean deleteCourseById(Long id);
    Boolean deleteAllCourses();

}
