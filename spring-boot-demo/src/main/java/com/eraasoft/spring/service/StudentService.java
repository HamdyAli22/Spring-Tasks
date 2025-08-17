package com.eraasoft.spring.service;

import com.eraasoft.spring.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto save(StudentDto student);
    StudentDto update(StudentDto student);
    Boolean delete(Long id);
    List<StudentDto> findAll();
    StudentDto findById(Long id);
    List<StudentDto> findByUsername(String username);
}
