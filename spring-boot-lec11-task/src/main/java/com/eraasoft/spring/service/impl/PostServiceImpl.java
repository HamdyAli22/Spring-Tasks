package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.dto.PostDto;
import com.eraasoft.spring.mapper.PostMapper;
import com.eraasoft.spring.model.Post;
import com.eraasoft.spring.model.User;
import com.eraasoft.spring.repo.PostRepo;
import com.eraasoft.spring.repo.UserRepo;
import com.eraasoft.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo  postRepo;
    private PostMapper postMapper;
    private UserRepo userRepo;

    @Autowired
    public PostServiceImpl(PostRepo postRepo, PostMapper postMapper,UserRepo userRepo) {
        this.postRepo = postRepo;
        this.postMapper = postMapper;
        this.userRepo = userRepo;
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        if(postDto.getUser() != null && postDto.getUser().getId() != null){
            User  user = userRepo.findById(postDto.getUser().getId()).orElseThrow(() -> new RuntimeException("user.not.found"));
        }

        if(Objects.nonNull(postDto.getId())){
            throw new RuntimeException("post.id.null");
        }

        Post post = postMapper.toPost(postDto);

        return postMapper.toPostDto(postRepo.save(post));
    }

    @Override
    public PostDto updatePost(PostDto postDto) {

        if(Objects.isNull(postDto.getId())){
            throw new RuntimeException("post.id.required");
        }

        postRepo.findById(postDto.getId()).orElseThrow(() -> new RuntimeException("post.not.found"));

        if (postDto.getUser() != null && postDto.getUser().getId() != null) {
                    userRepo.findById(postDto.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("user.not.found"));
        }

        Post post = postMapper.toPost(postDto);

        return  postMapper.toPostDto(postRepo.save(post));
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("post.id.not.found"));
        return postMapper.toPostDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return postMapper.toPostDtoList(posts);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("post.not.found"));
        postRepo.delete(post);
    }
}
