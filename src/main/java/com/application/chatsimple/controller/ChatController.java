package com.application.chatsimple.controller;

import com.application.chatsimple.data.Message;
import com.application.chatsimple.data.MessageOutput;
import com.application.chatsimple.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public MessageOutput sendMessage(Message message){
        LOGGER.info("Received message from {}, with text: {}", message.getFrom(), message.getText());
        return chatService.handleMessage(message);
    }

}
