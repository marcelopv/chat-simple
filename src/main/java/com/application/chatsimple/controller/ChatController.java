package com.application.chatsimple.controller;

import com.application.chatsimple.data.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message){
        LOGGER.info("Received message from {}, with text: {}", message.getFrom(), message.getText());
        return message;
    }

}
