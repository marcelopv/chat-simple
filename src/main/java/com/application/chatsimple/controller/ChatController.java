package com.application.chatsimple.controller;

import com.application.chatsimple.data.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message){
        return message;
    }

}
