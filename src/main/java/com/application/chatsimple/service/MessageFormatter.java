package com.application.chatsimple.service;

import com.application.chatsimple.data.Message;
import com.application.chatsimple.data.MessageOutput;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageFormatter {

    public static final String MESSAGE_TIME_FORMAT = "HH:mm";

    public MessageOutput format(Message message) {
        SimpleDateFormat sdf = new SimpleDateFormat(MESSAGE_TIME_FORMAT);
        String messageTime = sdf.format(new Date());

        return new MessageOutput(message.getFrom(), message.getText(), messageTime);
    }

}
