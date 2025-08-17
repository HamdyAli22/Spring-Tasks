package com.eraasoft.spring.controller;

import com.eraasoft.spring.dto.EmailDto;
import com.eraasoft.spring.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmailController {
    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/create-email")
    public ResponseEntity<EmailDto> createEmail(@RequestBody @Valid EmailDto emailDto,
                                                @RequestParam Long employeeId) {
        return ResponseEntity.ok(emailService.save(emailDto, employeeId));
    }

    @PostMapping("/update-email")
    public ResponseEntity<EmailDto> updateEmail(@RequestBody @Valid EmailDto emailDto,
                                                @RequestParam Long employeeId) {
        return ResponseEntity.ok(emailService.update(emailDto, employeeId));
    }

    @DeleteMapping("/delete-email")
    public ResponseEntity<Void> deleteEmail(@RequestParam Long emailId) {
        emailService.delete(emailId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/emails")
    public ResponseEntity<?> getEmails(@RequestParam(required = false) Long emailId) {
        if (emailId != null) {
            return ResponseEntity.ok(emailService.findById(emailId));
        }
        return ResponseEntity.ok(emailService.findAll());
    }

    @GetMapping("/email/by-name")
    public ResponseEntity<List<EmailDto>> getEmailByName(@RequestParam String name) {

        return ResponseEntity.ok(emailService.findByName(name));
    }

    @GetMapping("/email/by-names")
    public ResponseEntity<List<EmailDto>> getEmailByNames(@RequestParam List<String> names) {

        return ResponseEntity.ok(emailService.findByNames(names));
    }

    @GetMapping("/email/by-content")
    public ResponseEntity<List<EmailDto>> getEmailByContent(@RequestParam String content) {

        return ResponseEntity.ok(emailService.findByContent(content));
    }

}
