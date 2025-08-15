package com.unicourse.spring.service.impl;

import com.unicourse.spring.model.Course;
import com.unicourse.spring.model.Student;
import com.unicourse.spring.repo.CourseRepo;
import com.unicourse.spring.repo.StudentRepo;
import com.unicourse.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> saveAll(List<Student> students) {
        boolean hasInvalidId = students.stream().anyMatch(student -> Objects.nonNull(student.getId()));
        if (hasInvalidId) {
            throw new RuntimeException("id must be null for new student");
        }
        return studentRepo.saveAll(students);
    }

    @Override
    public Student registerStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        student.getCourses().add(course);

        return studentRepo.save(student);    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(()-> new RuntimeException("Student not found with id: " + studentId));
    }

    @Override
    public boolean deleteStudentById(Long studentId) {
        if(studentRepo.existsById(studentId)) {
            studentRepo.deleteById(studentId);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAllStudents() {
        List<Student> students = studentRepo.findAll();
        if(!students.isEmpty()) {
            studentRepo.deleteAll();
            return true;
        }
        return false;
    }
}
