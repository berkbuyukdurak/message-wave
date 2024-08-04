package com.bbd.messagewave.model.dto.message.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class GetAllSentMessagesResponseDTO {
    private UUID id;
    private LocalDateTime deliveredTime;
}
