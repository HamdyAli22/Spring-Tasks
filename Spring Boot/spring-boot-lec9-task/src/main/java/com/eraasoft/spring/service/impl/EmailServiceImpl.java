package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.dto.EmailDto;
import com.eraasoft.spring.mapper.EmailMapper;
import com.eraasoft.spring.model.Email;
import com.eraasoft.spring.model.Employee;
import com.eraasoft.spring.repo.EmailRepo;
import com.eraasoft.spring.repo.EmployeeRepo;
import com.eraasoft.spring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    private EmailRepo  emailRepo;
    private EmployeeRepo employeeRepo;
    private EmailMapper emailMapper;

    @Autowired
    public EmailServiceImpl(EmailRepo emailRepo, EmployeeRepo employeeRepo, EmailMapper emailMapper) {
        this.emailRepo = emailRepo;
        this.employeeRepo = employeeRepo;
        this.emailMapper = emailMapper;
    }

    @Override
    public EmailDto save(EmailDto emailDto, Long employeeId) {
        if (Objects.nonNull(emailDto.getId())) {
            throw new RuntimeException("email.id.null");
        }

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("employee.not.found"));

        if (emailRepo.existsByContent(emailDto.getContent())) {
            throw new RuntimeException("email.content.exists");
        }

        Email email = emailMapper.toEmail(emailDto);
        email.setEmployee(employee);

        email = emailRepo.save(email);
        EmailDto savedEmail = emailMapper.toEmailDto(email);
        savedEmail.setId(email.getId());
        return savedEmail;

    }

    @Override
    public EmailDto update(EmailDto emailDto, Long employeeId) {
        if(emailDto.getId() == null) {
            throw new RuntimeException("email.id.required");
        }
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> new RuntimeException("employee.not.found"));

        emailRepo.findById(emailDto.getId()).orElseThrow(() -> new RuntimeException("email.not.found"));

        Email email = emailMapper.toEmail(emailDto);

        email.setEmployee(employee);

        email = emailRepo.save(email);

        return emailMapper.toEmailDto(email);

    }

    @Override
    public void delete(Long id) {
        Email email = emailRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("email.not.found"));
        emailRepo.delete(email);
    }

    @Override
    public List<EmailDto> findAll() {
        List<Email> emails = emailRepo.findAll();
        return emailMapper.toEmailDtoList(emails);
    }

    @Override
    public EmailDto findById(Long id) {
        Email email =  emailRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("email.not.found"));
        return emailMapper.toEmailDto(email);
    }

    @Override
    public List<EmailDto> findByName(String name) {
        List<Email> emails = emailRepo.findByNameIgnoreCase(name);
        if (emails.isEmpty()) {
            throw new RuntimeException("email.not.found");
        }
        return emailMapper.toEmailDtoList(emails);
    }

    @Override
    public List<EmailDto> findByNames(List<String> names) {
        List<Email> emails = emailRepo.findByNameInIgnoreCase(names);
        if (emails.isEmpty()) {
            throw new RuntimeException("email.not.found");
        }
        return emailMapper.toEmailDtoList(emails);
    }

    @Override
    public List<EmailDto> findByContent(String content) {
        List<Email> emails = emailRepo.findByContentIgnoreCase(content);
        if (emails.isEmpty()) {
            throw new RuntimeException("email.not.found");
        }
        return emailMapper.toEmailDtoList(emails);
    }
}
