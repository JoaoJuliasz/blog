package com.juliasz.blog.service;

import com.juliasz.blog.model.Post;
import com.juliasz.blog.model.Tag;
import com.juliasz.blog.model.dto.PostDto;
import com.juliasz.blog.model.dto.UserPostsDto;
import com.juliasz.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final TagService tagService;
    private final SubscriptionService subscriptionService;

    public PostService(PostRepository postRepository, TagService tagService, SubscriptionService subscriptionService) {
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.subscriptionService = subscriptionService;
    }

    public Post findOne(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        if (foundPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        return foundPost.get();
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<UserPostsDto> findAllByUserId(Long id) {
        List<Post> posts = postRepository.findAllByCreatorId(id);
        return posts.stream().map(post -> new UserPostsDto(post.getTitle(), post.getId(), post.getImage(), post.getTags())).toList();
    }

    public Post createPost(PostDto postDto) {
        Set<Tag> tags = tagService.createAllTag(postDto.getTags());
        Post newPost = new Post(postDto.getTitle(), postDto.getSubtitle(), postDto.getImage(), postDto.getBody(), 0, 0, postDto.getCreatorId(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), tags);
        postRepository.save(newPost);
        subscriptionService.sendNotificationToSubscribers(newPost.getId(), newPost.getTitle(), newPost.getCreatorId());
        return newPost;
    }

    public Post updatePost(Map<String, Object> updates, Long id) {
        Post post = findOne(id);
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Post.class, key);
            if (field == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        String.format("The field %s does not exist", key)
                );
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, post, value);
        });
        postRepository.save(post);
        return post;
    }
}
