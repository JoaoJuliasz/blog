package com.juliasz.blog.model.dto;

public class SubscriptionBody {
    private Long creatorId;
    private Long subscriberId;

    public SubscriptionBody(Long creatorId, Long subscriberId) {
        this.creatorId = creatorId;
        this.subscriberId = subscriberId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }
}
