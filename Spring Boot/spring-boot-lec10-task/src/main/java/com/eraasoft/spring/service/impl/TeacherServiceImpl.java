package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.dto.TeacherDto;
import com.eraasoft.spring.mapper.TeacherMapper;
import com.eraasoft.spring.model.Teacher;
import com.eraasoft.spring.repo.TeacherRepo;
import com.eraasoft.spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepo teacherRepo;
    private TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepo teacherRepo, TeacherMapper teacherMapper) {
        this.teacherRepo = teacherRepo;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public List<TeacherDto> findAll() {
        List<Teacher> teachers = teacherRepo.findAll();
        return teacherMapper.toTeacherDtoList(teachers);
    }

    @Override
    public TeacherDto findById(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("teacher.not.found"));
        return teacherMapper.toTeacherDto(teacher);
    }
}
