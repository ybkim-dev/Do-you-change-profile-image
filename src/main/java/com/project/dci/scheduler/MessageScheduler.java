package com.project.dci.scheduler;

import com.project.dci.web.controller.api.MessageController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MessageScheduler {
    @Value("${config.api.send-message.query}")
    private String query;
    private final MessageController messageController;

    public MessageScheduler(MessageController messageController) {
        this.messageController = messageController;
    }

    @Scheduled(cron = "0 0 0/1 * * *")
    public void sendMessage() {
        messageController.getUsers(query);
    }
}
