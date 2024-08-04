package com.bbd.messagewave.service.message;

import com.bbd.messagewave.model.dto.message.request.WebhookRequestDTO;
import com.bbd.messagewave.model.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageSenderService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${messagewave.message.sender.webhook.url}")
    private String webhookUrl;

    @Value("${messagewave.message.sender.auth.key}")
    private String webhookAuthKey;

    public MessageSenderService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
    }

    public boolean sendMessage(Message message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ins-auth-key", webhookAuthKey);

        WebhookRequestDTO webhookRequest = new WebhookRequestDTO();
        webhookRequest.setTo(message.getRecipientPhoneNumber());
        webhookRequest.setContent(message.getContent());

        try {
            String payload = objectMapper.writeValueAsString(webhookRequest);
            HttpEntity<String> request = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);
            if (response.getStatusCode() == HttpStatus.ACCEPTED && response.getBody() != null) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
