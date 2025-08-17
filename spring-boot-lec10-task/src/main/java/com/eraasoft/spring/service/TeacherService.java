package com.eraasoft.spring.service;

import com.eraasoft.spring.dto.TeacherDto;
import java.util.List;

public interface TeacherService {
    List<TeacherDto> findAll();
    TeacherDto findById(Long id);
}
