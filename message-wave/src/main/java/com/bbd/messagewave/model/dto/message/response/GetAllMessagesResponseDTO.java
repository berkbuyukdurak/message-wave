package com.bbd.messagewave.model.dto.message.response;

import com.bbd.messagewave.constants.MessageSendingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class GetAllMessagesResponseDTO {
    private UUID id;
    private LocalDateTime deliveredTime;
    private String content;
    private String recipientPhoneNumber;
    private MessageSendingStatus sendingStatus;
}
