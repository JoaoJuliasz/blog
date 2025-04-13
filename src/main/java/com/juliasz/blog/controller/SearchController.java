package com.juliasz.blog.controller;

import com.juliasz.blog.model.dto.SearchResultDto;
import com.juliasz.blog.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public SearchResultDto search(@RequestParam String q) {
        return searchService.search(q);
    }
}
