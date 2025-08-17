package com.eraasoft.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {


    private Long id;

    @NotBlank(message = "student.username.required")
    private String fullUserName;

    @NotBlank(message = "student.password.required")
    private String Password;

    @NotNull(message = "student.age.required")
    @Min(value = 18 ,message = "student.age.min")
    @Max(value = 30 ,message = "student.age.max")
    private Integer age;
}
