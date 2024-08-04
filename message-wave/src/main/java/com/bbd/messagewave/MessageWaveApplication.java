package com.bbd.messagewave;

import com.bbd.messagewave.service.automation.AutomationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MessageWaveApplication implements ApplicationListener<ContextRefreshedEvent> {

    private final AutomationService automationService;

    public MessageWaveApplication(AutomationService automationService) {
        this.automationService = automationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageWaveApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        automationService.startProcessing();
    }
}
