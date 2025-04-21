package com.juliasz.blog.controller;

import com.juliasz.blog.model.Subscription;
import com.juliasz.blog.model.dto.SubscriptionBody;
import com.juliasz.blog.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public Subscription subscribe(@RequestBody SubscriptionBody subscriptionBody) {
        return subscriptionService.subscribeUser(subscriptionBody);
    }

    @DeleteMapping(value = "{id}")
    public void deleteSubscription(@PathVariable Long id) {
        this.subscriptionService.removeSubscription(id);
    }
}
