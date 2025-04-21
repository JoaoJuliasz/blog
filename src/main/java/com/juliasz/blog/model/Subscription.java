package com.juliasz.blog.model;

import com.juliasz.blog.model.dto.SubscriptionBody;
import jakarta.persistence.*;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "creator_id")
    private Long creatorId;
    @Column(name = "subscriber_id")
    private Long subscriberId;

    public Subscription() {}

    public Subscription(Long creatorId, Long subscriberId) {
        this.creatorId = creatorId;
        this.subscriberId = subscriberId;
    }

    public Subscription(SubscriptionBody subscriptionBody) {
        this.creatorId = subscriptionBody.getCreatorId();
        this.subscriberId = subscriptionBody.getSubscriberId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
