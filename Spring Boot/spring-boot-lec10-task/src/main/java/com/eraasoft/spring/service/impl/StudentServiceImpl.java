package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.dto.StudentDto;
import com.eraasoft.spring.mapper.StudentMapper;
import com.eraasoft.spring.model.Student;
import com.eraasoft.spring.repo.StudentRepo;
import com.eraasoft.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepo studentRepo;
    private StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, StudentMapper studentMapper) {
        this.studentRepo = studentRepo;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepo.findAll();
        return studentMapper.toStudentDtoList(students);
    }

    @Override
    public StudentDto findById(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("student.not.found"));
        return studentMapper.toStudentDto(student);
    }
}
