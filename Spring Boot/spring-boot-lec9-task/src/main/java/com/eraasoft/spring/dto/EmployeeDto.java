package com.eraasoft.spring.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private  Long id;

    @NotBlank(message = "employee.name.required")
    private String name;

    @NotNull(message = "employee.age.required")
    @Min(value = 15 ,message = "employee.age.min")
    @Max(value = 40 ,message = "employee.age.max")
    private Integer age;

    @NotNull(message = "employee.salary.required")
    @Min(value = 5000 ,message = "employee.salary.min")
    @Max(value = 10000 ,message = "employee.salary.max")
    private Double salary;

    @Valid
    private List<EmailDto> emails;
}
