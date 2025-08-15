package com.eraasoft.spring.mapper;

import com.eraasoft.spring.dto.PostDto;
import com.eraasoft.spring.dto.UserDto;
import com.eraasoft.spring.model.Post;
import com.eraasoft.spring.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPost (PostDto postDto);

    @Mapping(target = "user", expression = "java(toUserDtoWithoutPosts(post.getUser()))")
    PostDto toPostDto (Post post);

    List<Post> toPostList (List<PostDto> postDto);
    List<PostDto> toPostDtoList (List<Post> posts);

    default UserDto toUserDtoWithoutPosts(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getPassword(),
                null
        );
    }

}
