package com.bbd.messagewave.repository;

import com.bbd.messagewave.constants.MessageSendingStatus;
import com.bbd.messagewave.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findAllBySendingStatus(MessageSendingStatus status);
}
