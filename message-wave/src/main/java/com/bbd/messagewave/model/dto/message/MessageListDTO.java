package com.bbd.messagewave.model.dto.message;

import com.bbd.messagewave.model.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageListDTO {
    private List<Message> messages;
}
