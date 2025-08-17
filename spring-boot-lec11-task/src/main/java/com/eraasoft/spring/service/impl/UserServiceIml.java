package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.dto.PostDto;
import com.eraasoft.spring.dto.UserDto;
import com.eraasoft.spring.mapper.PostMapper;
import com.eraasoft.spring.mapper.UserMapper;
import com.eraasoft.spring.model.User;
import com.eraasoft.spring.repo.PostRepo;
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

    private final PostMapper postMapper;
    private UserRepo userRepo;
    private UserMapper userMapper;
    private PostRepo postRepo;

    public UserServiceIml(UserRepo userRepo, UserMapper userMapper, PostRepo postRepo, PostMapper postMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.postRepo = postRepo;
        this.postMapper = postMapper;
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


    @Override
    public UserDto updateUser(UserDto userDto) {

        if(Objects.isNull(userDto.getId())){
            throw new RuntimeException("user.id.required");
        }

        userRepo.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("user.not.found"));

        if(userDto.getPosts() != null) {
            userDto.getPosts().forEach(postDto -> {
                if(Objects.nonNull(postDto.getId())){
                    postRepo.findById(postDto.getId())
                            .orElseThrow(() -> new RuntimeException("post.not.found"));
                }
            });
        }

        User user  = userMapper.toUser(userDto);

        if (user.getPosts() != null) {
            user.getPosts().forEach(post -> post.setUser(user));
        }

        userRepo.save(user);
        return userMapper.toUserDto(user);
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

    @Override
    public List<PostDto> getPostsByUserId(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("user.not.found"));
        if(user.getPosts() == null ||  user.getPosts().isEmpty()) {
            throw new RuntimeException("user.posts.not.found");
        }
        return postMapper.toPostDtoList(user.getPosts());
    }
}
