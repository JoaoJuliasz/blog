package com.juliasz.blog.model.dto;

import java.util.List;

public class NotificationIdsDto {
    private List<Long> usersIds;
    private String title;
    private Long postId;

    public NotificationIdsDto(List<Long> usersIds, String title, Long postId) {
        this.usersIds = usersIds;
        this.title = title;
        this.postId = postId;
    }

    public List<Long> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(List<Long> usersIds) {
        this.usersIds = usersIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
