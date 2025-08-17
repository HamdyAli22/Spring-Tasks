package com.unicourse.spring.service.impl;

import com.unicourse.spring.model.Instructor;
import com.unicourse.spring.repo.InstructorRepo;
import com.unicourse.spring.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepo instructorRepo;

    @Autowired
    public InstructorServiceImpl(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    @Override
    public List<Instructor> saveAll(List<Instructor> instructors) {
        boolean hasInvalidId = instructors.stream().anyMatch(instructor -> Objects.nonNull(instructor.getId()));
        if (hasInvalidId) {
            throw new RuntimeException("id must be null for new instructor");
        }
        return instructorRepo.saveAll(instructors);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }

    @Override
    public Boolean deleteInstructorById(Long id) {
        if (instructorRepo.existsById(id)) {
            instructorRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAllInstructors() {
        List<Instructor> instructors = instructorRepo.findAll();
        if (!instructors.isEmpty()) {
            instructorRepo.deleteAll();
            return true;
        }
        return false;
    }

}
