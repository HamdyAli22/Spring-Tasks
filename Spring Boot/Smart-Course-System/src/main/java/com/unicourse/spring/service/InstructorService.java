package com.unicourse.spring.service;

import com.unicourse.spring.model.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> saveAll(List<Instructor> instructors);
    Instructor getInstructorById(Long id);
    List<Instructor> getAllInstructors();
    Boolean deleteInstructorById(Long id);
    Boolean deleteAllInstructors();
}
