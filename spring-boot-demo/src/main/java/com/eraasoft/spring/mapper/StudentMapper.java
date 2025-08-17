package com.eraasoft.spring.mapper;


import com.eraasoft.spring.dto.StudentDto;
import com.eraasoft.spring.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface StudentMapper {
    //StudentMapper STUDENT_MAPPER = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "fullUserName",target = "userName")
    Student toStudent(StudentDto studentDto);

    @Mapping(source = "userName",target = "fullUserName")
    StudentDto toStudentDto(Student student);

    List<Student>  toStudentList(List<StudentDto> studentDtoList);

    List<StudentDto> toStudentDtoList(List<Student> studentList);
}
