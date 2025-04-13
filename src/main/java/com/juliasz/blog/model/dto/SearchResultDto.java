package com.juliasz.blog.model.dto;

import com.juliasz.blog.model.Post;

import java.util.List;

public class SearchResultDto {
    private final List<UserWithPostsDto> users;
    private final List<Post> posts;

    public SearchResultDto(List<UserWithPostsDto> users, List<Post> posts) {
        this.users = users;
        this.posts = posts;
    }

    public List<UserWithPostsDto> getUsers() {
        return users;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
