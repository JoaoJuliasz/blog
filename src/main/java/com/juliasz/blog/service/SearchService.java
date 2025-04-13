package com.juliasz.blog.service;

import com.juliasz.blog.model.Post;
import com.juliasz.blog.model.User;
import com.juliasz.blog.model.dto.SearchResultDto;
import com.juliasz.blog.model.dto.UserWithPostsDto;
import com.juliasz.blog.repository.PostRepository;
import com.juliasz.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public SearchService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public SearchResultDto search(String searchQuery) {
        List<User> users = userRepository.searchUsers(searchQuery);
        List<Post> posts = postRepository.searchPosts(searchQuery);
        List<UserWithPostsDto> usersWithPosts = users.stream().map(user -> new UserWithPostsDto(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getAbout(), user.getImage(), user.getSocialMedia(), Collections.emptyList())).toList();

        return new SearchResultDto(usersWithPosts, posts);
    }
}
