package com.eraasoft.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class EmailDto {

    private Long id;

    @NotBlank(message = "email.name.required")
    private String name;

    @NotBlank(message = "email.content.required")
    @Email(message = "email.content.match")
    private String content;


}
