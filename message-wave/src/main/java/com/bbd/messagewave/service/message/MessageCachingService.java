package com.bbd.messagewave.service.message;

import com.bbd.messagewave.model.dto.message.response.GetAllSentMessagesResponseDTO;
import com.bbd.messagewave.model.entity.Message;
import com.bbd.messagewave.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageCachingService {

    private final RedisService redisService;
    private final ObjectMapper objectMapper;

    @Value("${messagewave.redis.key.sentMessagePrefix}")
    private String sentMessagePrefix;


    public MessageCachingService(RedisService redisService, ObjectMapper objectMapper) {
        this.redisService = redisService;
        this.objectMapper = objectMapper;
    }

    public void cacheSentMessage(Message message) {
        try {
            String redisKey = sentMessagePrefix + message.getId();
            GetAllSentMessagesResponseDTO sentMessage = GetAllSentMessagesResponseDTO.builder()
                    .id(message.getId())
                    .deliveredTime(message.getDeliveredTime())
                    .build();
            redisService.setValue(redisKey, objectMapper.writeValueAsString(sentMessage));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
