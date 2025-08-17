package com.eraasoft.spring.service;

import com.eraasoft.spring.dto.PostDto;
import com.eraasoft.spring.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    List<PostDto>getPostsByUserId(Long id);
}
