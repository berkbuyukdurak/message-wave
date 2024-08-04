package com.bbd.messagewave.model.dto.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageResponseDTO {
    private String message;
    private String messageId;
}
