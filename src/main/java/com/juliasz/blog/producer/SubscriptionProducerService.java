package com.juliasz.blog.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliasz.blog.model.dto.NotificationIdsDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionProducerService {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SubscriptionProducerService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendNotification(NotificationIdsDto notificationIdsDto) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "subscription-request-exchange",
                "subscription-request-route-key",
                objectMapper.writeValueAsString(notificationIdsDto));
    }

}
