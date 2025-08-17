package com.eraasoft.spring.service;

import com.eraasoft.spring.dto.PostDto;

import java.util.List;

public interface PostService {
   PostDto createPost(PostDto postDto);
   PostDto updatePost(PostDto postDto);
   PostDto getPostById(Long id);
   List<PostDto> getAllPosts();
   void deletePostById(Long id);
}
