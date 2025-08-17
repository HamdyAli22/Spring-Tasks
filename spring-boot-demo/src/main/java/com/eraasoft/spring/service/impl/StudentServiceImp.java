package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.mapper.StudentMapper;
import com.eraasoft.spring.model.Student;
import com.eraasoft.spring.dto.StudentDto;
import com.eraasoft.spring.repo.StudentRepo;
import com.eraasoft.spring.service.StudentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {

private StudentRepo studentRepo;
//private ModelMapper modelMapper;
private StudentMapper studentMapper;

@Autowired
    public StudentServiceImp(StudentRepo studentRepo,StudentMapper studentMapper) {
        this.studentRepo = studentRepo;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
    if(Objects.nonNull(studentDto.getId())){
        throw new RuntimeException("student.id.null");
    }
//    if(Objects.isNull(studentDto.getFullUserName())){
//        throw new RuntimeException("username must be not null");
//    }
//    if(Objects.isNull(studentDto.getPassword())){
//        throw new RuntimeException("password must be not null");
//    }
//    if(Objects.isNull(studentDto.getAge())){
//        throw new RuntimeException("age must be not null");
//    }
    if (!studentRepo.extractByUserName(studentDto.getFullUserName()).isEmpty()) {
        throw new RuntimeException("student.username.exists");
    }

        //Student student = modelMapper.map(studentDto,Student.class);
        Student student = studentMapper.toStudent(studentDto);
        student = studentRepo.save(student);
    studentDto.setId(student.getId());
    return studentDto;
    }

    @Override
    public StudentDto update(StudentDto studentDto) {
        if(Objects.isNull(studentDto.getId())){
            throw new RuntimeException("student.id.required");
        }
        //Student student = modelMapper.map(studentDto,Student.class);
        Student student = studentMapper.toStudent(studentDto);
        studentRepo.save(student);
        return studentDto;
    }

    @Override
    public Boolean delete(Long id) {
        if (!studentRepo.existsById(id)) {
            return false;
        }
        studentRepo.deleteById(id);
        return true;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepo.findAll();
        if(CollectionUtils.isEmpty(students)){
            return new ArrayList<>();
        }
//        List<StudentDto> studentDtos = students.stream()
//                .map(student -> modelMapper.map(student,StudentDto.class)).collect(Collectors.toList());
        List<StudentDto> studentDtos = students.stream()
                .map(studentMapper::toStudentDto).collect(Collectors.toList());
        return studentDtos;
    }

    @Override
    public StudentDto findById(Long id) {
        Optional<Student> studentOptional = studentRepo.findById(id);
        if(!studentOptional.isPresent()){
            return null;
        }
    //   return modelMapper.map(studentOptional.get(),StudentDto.class);
        return studentMapper.toStudentDto(studentOptional.get());
    }

    @Override
    public List<StudentDto> findByUsername(String username) {
    List<Student> students = studentRepo.extractByUserName(username);
//    List<StudentDto> studentDtos = students.stream()
//            .map(student -> modelMapper.map(student,StudentDto.class)).collect(Collectors.toList());
        List<StudentDto> studentDtos = students.stream()
                .map(studentMapper::toStudentDto).collect(Collectors.toList());
        return studentDtos;
    }
}
