package com.eraasoft.spring.mapper;

import com.eraasoft.spring.dto.EmailDto;
import com.eraasoft.spring.model.Email;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    Email toEmail(EmailDto emailDto);

    EmailDto toEmailDto(Email email);

    List<Email> toEmailList(List<EmailDto> emailDtoList);

    List<EmailDto> toEmailDtoList(List<Email> emailList);

}
