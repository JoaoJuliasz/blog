package com.juliasz.blog.model.dto;

import com.juliasz.blog.model.Tag;

import java.util.HashSet;
import java.util.Set;

public class PostDto {
    private String title;
    private String subtitle;
    private String image;
    private String body;
    private Long creatorId;
    private Set<Tag> tags = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImage() {
        return image;
    }

    public String getBody() {
        return body;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
