package com.juliasz.blog.model.dto;

import com.juliasz.blog.model.SocialMedia;

import java.util.List;

public class UserWithPostsDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String about;
    private String image;
    private SocialMedia socialMedia;
    private List<UserPostsDto> posts;

    public UserWithPostsDto(Long id, String firstName, String lastName, String email, String about, String image, SocialMedia socialMedia, List<UserPostsDto> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.about = about;
        this.image = image;
        this.socialMedia = socialMedia;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAbout() {
        return about;
    }

    public String getImage() {
        return image;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public List<UserPostsDto> getPosts() {
        return posts;
    }
}
