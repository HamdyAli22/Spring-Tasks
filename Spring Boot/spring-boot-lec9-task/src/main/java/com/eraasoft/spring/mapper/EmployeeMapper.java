package com.eraasoft.spring.mapper;

import com.eraasoft.spring.dto.EmployeeDto;
import com.eraasoft.spring.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeDto employeeDto);

    EmployeeDto toEmployeeDto(Employee employee);

    List<Employee> toEmployeeList(List<EmployeeDto> employeeDto);

    List<EmployeeDto> toEmployeeDtoList(List<Employee> employees);

}
