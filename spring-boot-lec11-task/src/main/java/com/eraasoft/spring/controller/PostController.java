package com.eraasoft.spring.controller;

import com.eraasoft.spring.dto.PostDto;
import com.eraasoft.spring.dto.UserDto;
import com.eraasoft.spring.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/save-post")
    ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto  postDto){
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @PutMapping("/update-post")
    ResponseEntity<PostDto> updatePost(@RequestBody @Valid PostDto  postDto){
        return ResponseEntity.ok(postService.updatePost(postDto));
    }

    @DeleteMapping("delete-post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/{id}")
    ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
