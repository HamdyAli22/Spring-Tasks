package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.dto.UserDto;
import com.eraasoft.spring.mapper.UserMapper;
import com.eraasoft.spring.model.User;
import com.eraasoft.spring.repo.UserRepo;
import com.eraasoft.spring.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceIml implements UserService {

    private UserRepo userRepo;
    private UserMapper userMapper;
    private EntityManager entityManager;

    public UserServiceIml(UserRepo userRepo, UserMapper userMapper, EntityManager entityManager) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.entityManager = entityManager;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if(Objects.nonNull(userDto.getId())){
            throw new RuntimeException("user.id.null");
        }
        if(userRepo.existsByName(userDto.getName())){
            throw new RuntimeException("user.name.exists");
        }
        User user = userMapper.toUser(userDto);
        if (user.getPosts() != null) {
            user.getPosts().forEach(post -> post.setUser(user));
        }
        return userMapper.toUserDto(userRepo.save(user));
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {

        if(Objects.isNull(userDto.getId())){
            throw new RuntimeException("user.id.required");
        }

        userRepo.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("user.not.found"));

        User detachedUser  = userMapper.toUser(userDto);

        if (detachedUser.getPosts() != null) {
            detachedUser.getPosts().forEach(p -> p.setUser(detachedUser));
        }

        User merged = entityManager.merge(detachedUser);

        return userMapper.toUserDto(userRepo.save(merged));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("user.not.found"));
        userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("user.not.found"));
        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return userMapper.toUserDtoList(users);
    }
}
