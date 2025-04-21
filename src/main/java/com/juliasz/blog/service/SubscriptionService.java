package com.juliasz.blog.service;

import com.juliasz.blog.model.Subscription;
import com.juliasz.blog.model.dto.SubscriptionBody;
import com.juliasz.blog.repository.SubscriptionRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription subscribeUser(SubscriptionBody subscriptionBody) {
        Subscription subscription = new Subscription(subscriptionBody);
        subscriptionRepository.save(subscription);
        return subscription;
    }

    public void removeSubscription(Long subscriptionId) {
        findOne(subscriptionId);
        subscriptionRepository.deleteById(subscriptionId);
    }

    public void sendNotificationToSubscribers(Long creatorId) {
        List<Long> subscribersIds = subscriptionRepository.findAllSubscriber(creatorId);
    }

    private Subscription findOne(Long subscriptionId) {
        Optional<Subscription> foundSubscription = subscriptionRepository.findById(subscriptionId);
        if(foundSubscription.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found");
        }
        return foundSubscription.get();
    }
}
