package com.bbd.messagewave.service.worker;

import com.bbd.messagewave.constants.MessageSendingStatus;
import com.bbd.messagewave.model.entity.Message;
import com.bbd.messagewave.repository.MessageRepository;
import com.bbd.messagewave.service.message.MessageCachingService;
import com.bbd.messagewave.service.message.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class MessageReceiver {

    private final MessageRepository messageRepository;
    private final MessageSenderService messageSenderService;
    private final MessageCachingService messageCachingService;

    @Autowired
    public MessageReceiver(
            MessageRepository messageRepository,
            MessageSenderService messageSenderService,
            MessageCachingService messageCachingService
    ) {
        this.messageRepository = messageRepository;
        this.messageSenderService = messageSenderService;
        this.messageCachingService = messageCachingService;
    }

    public void receiveMessage(Message message) {
        boolean isSent = messageSenderService.sendMessage(message);

        if (isSent) {
            message.setSendingStatus(MessageSendingStatus.DELIVERED);
            message.setDeliveredTime(LocalDateTime.now());
            // Cache messageId and sending time in Redis
            messageCachingService.cacheSentMessage(message);
        } else {
            // TODO: Add not delivered messages to the Dead Letter Queue
            message.setSendingStatus(MessageSendingStatus.NOT_DELIVERED);
        }

        messageRepository.save(message);
    }
}
