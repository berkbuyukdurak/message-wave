package com.bbd.messagewave.service.automation;

import com.bbd.messagewave.constants.MessageSendingStatus;
import com.bbd.messagewave.model.entity.Message;
import com.bbd.messagewave.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class AutomationService {

    @Value("${messagewave.rabbitmq.queue.name}")
    private String queueName;

    private static final long MESSAGE_PROCESSING_PERIOD = 120000;

    private static final Logger log = LoggerFactory.getLogger(AutomationService.class);

    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;
    private final TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
    private ScheduledFuture<?> scheduledFuture;

    public AutomationService(MessageRepository messageRepository, RabbitTemplate rabbitTemplate) {
        this.messageRepository = messageRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void startProcessing() {
        log.info("Starting message processing");
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            scheduledFuture = taskScheduler.scheduleAtFixedRate(this::processUnsentMessages, MESSAGE_PROCESSING_PERIOD);
        }
    }

    public void stopProcessing() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            log.info("Stopped message processing");
        }
    }

    @PostConstruct
    public void onStartup() {
        startProcessing();
    }

    @PreDestroy
    public void onShutdown() {
        stopProcessing();
    }

    public void processUnsentMessages() {
        List<Message> unsentMessages = messageRepository.findAllBySendingStatus(MessageSendingStatus.PENDING);
        unsentMessages.forEach(message -> {
            rabbitTemplate.convertAndSend(queueName, message);
            log.info("Processed message: {}", message.getId());
        });
    }
}
