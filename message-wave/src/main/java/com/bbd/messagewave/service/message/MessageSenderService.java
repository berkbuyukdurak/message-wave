package com.bbd.messagewave.service.message;

import com.bbd.messagewave.model.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageSenderService {

    private final RestTemplate restTemplate;

    @Value("${messagewave.message.sender.webhook.url}")
    private String webhookUrl;

    @Value("${messagewave.message.sender.auth.key}")
    private String webhookAuthKey;

    public MessageSenderService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean sendMessage(Message message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ins-auth-key", webhookAuthKey);

        String payload = String.format("{\"to\":\"%s\", \"content\":\"%s\"}",
                message.getRecipientPhoneNumber(), message.getContent());

        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);
            if (response.getStatusCode() == HttpStatus.ACCEPTED && response.getBody() != null) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
