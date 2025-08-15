package com.eraasoft.spring.service;

import com.eraasoft.spring.dto.EmailDto;

import java.util.List;

public interface EmailService {
    EmailDto save(EmailDto emailDto, Long employeeId);
    EmailDto  update(EmailDto emailDto, Long employeeId);
    void delete(Long id);
    List<EmailDto> findAll();
    EmailDto findById(Long id);
    List<EmailDto> findByName(String name);
    List<EmailDto> findByNames(List<String> names);
    List<EmailDto> findByContent(String content);
}
