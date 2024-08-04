package com.bbd.messagewave.model.entity;

import com.bbd.messagewave.constants.MessageSendingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "delivered_time")
    private LocalDateTime deliveredTime;

    @Column(name = "content", nullable = false, length = 512)
    private String content;

    @Column(name = "recipient_phone_number", nullable = false)
    private String recipientPhoneNumber;

    @Column(name = "sending_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageSendingStatus sendingStatus = MessageSendingStatus.PENDING;
}
