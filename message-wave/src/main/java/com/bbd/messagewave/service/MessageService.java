package com.bbd.messagewave.service;

import com.bbd.messagewave.model.dto.message.response.GetAllMessagesResponseDTO;
import com.bbd.messagewave.model.dto.message.response.GetAllSentMessagesResponseDTO;
import com.bbd.messagewave.model.entity.Message;
import com.bbd.messagewave.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;

    @Value("${messagewave.redis.key.sentMessagePrefix}")
    private String sentMessagePrefix;

    public MessageService(MessageRepository messageRepository, RedisService redisService, ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;
        this.redisService = redisService;
        this.objectMapper = objectMapper;
    }

    public List<GetAllMessagesResponseDTO> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(this::convertToGetAllMessagesResponseDTO)
                .collect(Collectors.toList());
    }

    public List<GetAllSentMessagesResponseDTO> getAllSentMessages() {
        Set<String> keys = redisService.getKeys(sentMessagePrefix + "*");
        List<GetAllSentMessagesResponseDTO> sentMessages = new ArrayList<>();
        for (String key : keys) {
            String jsonData = redisService.getValue(key);
            try {
                GetAllSentMessagesResponseDTO message = objectMapper.readValue(jsonData, GetAllSentMessagesResponseDTO.class);
                sentMessages.add(message);
            } catch (Exception e) {
                // Handle JSON parsing errors
            }
        }
        return sentMessages;
    }

    private GetAllMessagesResponseDTO convertToGetAllMessagesResponseDTO(Message message) {
        return GetAllMessagesResponseDTO.builder()
                .id(message.getId())
                .deliveredTime(message.getDeliveredTime())
                .content(message.getContent())
                .recipientPhoneNumber(message.getRecipientPhoneNumber())
                .sendingStatus(message.getSendingStatus())
                .build();
    }
}
