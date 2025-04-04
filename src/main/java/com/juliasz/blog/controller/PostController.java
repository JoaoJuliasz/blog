package com.juliasz.blog.controller;

import com.juliasz.blog.model.Post;
import com.juliasz.blog.model.dto.PostDto;
import com.juliasz.blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping(value = "{id}")
    public Post findOne(@PathVariable Long id) {
        return postService.findOne(id);
    }

    @PostMapping
    public Post createOne(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @PutMapping(value = "{id}")
    public Post updateOne(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return  postService.updatePost(updates, id);
    }
}
