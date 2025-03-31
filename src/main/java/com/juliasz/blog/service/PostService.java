package com.juliasz.blog.service;

import com.juliasz.blog.model.Post;
import com.juliasz.blog.model.Tag;
import com.juliasz.blog.model.dto.PostDto;
import com.juliasz.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final TagService tagService;

    public PostService(PostRepository postRepository, TagService tagService) {
        this.postRepository = postRepository;
        this.tagService = tagService;
    }

    public Post findOne(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        if (foundPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        return foundPost.get();
    }

    public Post createPost(PostDto postDto) {
        Set<Tag> tags = tagService.createAllTag(postDto.getTags());
        Post newPost = new Post(postDto.getTitle(), postDto.getSubtitle(), postDto.getImage(), postDto.getBody(), 0, 0, postDto.getCreatorId(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), tags);
        postRepository.save(newPost);
        return newPost;
    }
}
