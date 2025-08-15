package com.eraasoft.spring.mapper;

import com.eraasoft.spring.dto.StudentDto;
import com.eraasoft.spring.dto.TeacherDto;
import com.eraasoft.spring.model.Student;
import com.eraasoft.spring.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { TeacherMapper.class })
public interface TeacherMapper {
    Teacher toTeacher(TeacherDto teacherDto);

    @Mapping(target = "students", expression = "java(toStudentDtoWithoutTeachersList(teacher.getStudents()))")
    TeacherDto toTeacherDto(Teacher teacher);

    List<Teacher> toTeacherList(List<TeacherDto> teacherDto);
    List<TeacherDto> toTeacherDtoList(List<Teacher> teachers);

    default List<StudentDto> toStudentDtoWithoutTeachersList(List<Student> students) {
        if (students == null) return null;
        return students.stream()
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getAge(), null))
                .toList();
    }
}
