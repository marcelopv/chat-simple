package com.application.chatsimple.service;

import com.application.chatsimple.data.Message;
import com.application.chatsimple.data.MessageOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final MessageFormatter messageFormatter;

    @Autowired
    public ChatService(MessageFormatter messageFormatter){
        this.messageFormatter = messageFormatter;
    }

    public MessageOutput sendMessage(Message message) {
        return messageFormatter.format(message);
    }
}
