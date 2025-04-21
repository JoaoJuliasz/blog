package com.juliasz.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.juliasz.blog.model.Subscription;
import com.juliasz.blog.model.dto.NotificationIdsDto;
import com.juliasz.blog.model.dto.SubscriptionBody;
import com.juliasz.blog.producer.SubscriptionProducerService;
import com.juliasz.blog.repository.SubscriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionProducerService subscriptionProducerService;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionProducerService subscriptionProducerService) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionProducerService = subscriptionProducerService;
    }

    public Subscription subscribeUser(SubscriptionBody subscriptionBody) {
        Subscription subscription = new Subscription(subscriptionBody);
        subscriptionRepository.save(subscription);
        return subscription;
    }

    public void removeSubscription(Long subscriptionId) {
        validateSubscription(subscriptionId);
        subscriptionRepository.deleteById(subscriptionId);
    }

    public void sendNotificationToSubscribers(Long postId, String title, Long creatorId) {
        List<Long> subscribersIds = subscriptionRepository.findAllSubscriber(creatorId);
        NotificationIdsDto notificationIdsDto =  new NotificationIdsDto(subscribersIds, title, postId);
        try {
            subscriptionProducerService.sendNotification(notificationIdsDto);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void validateSubscription(Long subscriptionId) {
        Optional<Subscription> foundSubscription = subscriptionRepository.findById(subscriptionId);
        if(foundSubscription.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found");
        }
    }
}
