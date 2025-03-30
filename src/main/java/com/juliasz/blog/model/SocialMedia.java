package com.juliasz.blog.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class SocialMedia {
    private SocialMediaType type;
    private String link;

    public SocialMedia(SocialMediaType type, String link) {
        this.type = type;
        this.link = link;
    }

    public SocialMedia() {
    }

    public SocialMediaType getType() {
        return type;
    }

    public void setType(SocialMediaType type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
