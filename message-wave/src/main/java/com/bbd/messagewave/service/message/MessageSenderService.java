package com.bbd.messagewave.service.message;

import com.bbd.messagewave.model.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageSenderService {

    private final RestTemplate restTemplate;

    @Value("${messagewave.message.sender.webhook.url}")
    private String webhookUrl;

    public MessageSenderService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean sendMessage(Message message) {
        try {
            // Simulate sending the message
            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, message, String.class);
            return response.getStatusCode() == HttpStatus.ACCEPTED;
        } catch (Exception e) {
            return false;
        }
    }
}
