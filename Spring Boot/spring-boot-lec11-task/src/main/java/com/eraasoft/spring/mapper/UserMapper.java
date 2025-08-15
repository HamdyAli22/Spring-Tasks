package com.eraasoft.spring.mapper;

import com.eraasoft.spring.dto.PostDto;
import com.eraasoft.spring.dto.UserDto;
import com.eraasoft.spring.model.Post;
import com.eraasoft.spring.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    @Mapping(target = "posts", expression = "java(toPostDtoWithoutUserList(user.getPosts()))")
    UserDto toUserDto(User user);

    List<User> toUserList(List<UserDto> useDto);
    List<UserDto> toUserDtoList(List<User> users);

    default List<PostDto> toPostDtoWithoutUserList(List<Post> posts) {
        if (posts == null) {
            return new ArrayList<>();
        }
        return posts.stream()
                .map(post -> new PostDto(post.getId(), post.getText(), post.getImagePath(), null))
                .toList();
    }
}
