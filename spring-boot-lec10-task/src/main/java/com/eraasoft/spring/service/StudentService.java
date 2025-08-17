package com.eraasoft.spring.service;

import com.eraasoft.spring.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();
    StudentDto findById(Long id);
}
