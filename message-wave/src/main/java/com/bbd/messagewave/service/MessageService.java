package com.bbd.messagewave.service;

import com.bbd.messagewave.constants.MessageSendingStatus;
import com.bbd.messagewave.model.dto.message.response.GetAllMessagesResponseDTO;
import com.bbd.messagewave.model.entity.Message;
import com.bbd.messagewave.repository.MessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<GetAllMessagesResponseDTO> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(this::convertToGetAllMessagesResponseDTO)
                .collect(Collectors.toList());
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
