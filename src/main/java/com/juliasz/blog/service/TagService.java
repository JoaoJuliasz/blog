package com.juliasz.blog.service;

import com.juliasz.blog.model.Tag;
import com.juliasz.blog.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Set<Tag> createAllTag(Set<Tag> tags) {
        Set<Tag> managedTags = new HashSet<>();
        tags.forEach(t -> {
            Tag existingTag = tagRepository.findByName(t.getName());
            if(existingTag == null) {
                existingTag = tagRepository.save(t);
            }
            managedTags.add(existingTag);
        });
        return managedTags;
    }
}
