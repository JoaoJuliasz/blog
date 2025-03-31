package com.juliasz.blog.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String subtitle;
    private String image;
    private String body;
    private int likes;
    private int dislikes;
    @Column(name = "creator_id", nullable = false)
    private Long creatorId;
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
        @ManyToMany
        @JoinTable(
                name = "post_tags",
                joinColumns = @JoinColumn(name = "post_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
        private Set<Tag> tags = new HashSet<>();

    public Post() {
    }

    public Post( String title, String subtitle, String image, String body, int likes, int dislikes, Long creatorId, Timestamp createdAt, Timestamp updatedAt, Set<Tag> tags) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.body = body;
        this.likes = likes;
        this.dislikes = dislikes;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
