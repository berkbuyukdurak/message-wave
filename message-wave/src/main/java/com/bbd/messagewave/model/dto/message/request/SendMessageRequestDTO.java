package com.bbd.messagewave.model.dto.message.request;

import lombok.Data;

@Data
public class SendMessageRequestDTO {
    private String content;
    private String recipientPhoneNumber;
}
