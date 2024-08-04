package com.bbd.messagewave.model.dto.message.request;

import lombok.Data;

@Data
public class WebhookRequestDTO {
    private String to;
    private String content;
}
