package com.juliasz.blog.model.dto;

import com.juliasz.blog.model.Tag;

import java.util.Set;

public class UserPostsDto {
    private final String title;
    private final Long id;
    private final String image;
    private final Set<Tag> tags;

    public UserPostsDto(String title, Long id, String image, Set<Tag> tags) {
        this.title = title;
        this.id = id;
        this.image = image;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
