package com.eraasoft.spring.mapper;

import com.eraasoft.spring.dto.StudentDto;
import com.eraasoft.spring.dto.TeacherDto;
import com.eraasoft.spring.model.Student;
import com.eraasoft.spring.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface StudentMapper {
    Student toStudent(StudentDto studentDto);

    @Mapping(target = "teachers" , expression = "java(toTeacherDtoWithoutStudentsList(student.getTeachers()))")
    StudentDto toStudentDto(Student student);

    List<Student> toStudentList(List<StudentDto> studentDto);
    List<StudentDto> toStudentDtoList(List<Student> students);

   default List<TeacherDto> toTeacherDtoWithoutStudentsList(List<Teacher> teachers){
        if(teachers == null){
            return new ArrayList<>();
        }
        return teachers.stream()
                .map(teacher -> new TeacherDto(teacher.getId(),teacher.getName(),teacher.getSubject(),null))
                .toList();
    }
}
