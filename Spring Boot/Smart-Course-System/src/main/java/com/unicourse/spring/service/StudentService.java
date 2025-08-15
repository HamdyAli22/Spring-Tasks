package com.unicourse.spring.service;

import com.unicourse.spring.model.Student;

import java.util.List;

public interface StudentService {
 List<Student> saveAll(List<Student> students);
 Student registerStudentToCourse(Long studentId, Long courseId);
 List<Student> getAllStudents();
 Student getStudentById(Long studentId);
 boolean deleteStudentById(Long studentId);
 boolean deleteAllStudents();
}
