package com.eraasoft.spring.config;

import com.eraasoft.spring.dto.StudentDto;
import com.eraasoft.spring.model.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }

}
