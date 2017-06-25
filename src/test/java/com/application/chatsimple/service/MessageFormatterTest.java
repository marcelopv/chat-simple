package com.application.chatsimple.service;

import com.application.chatsimple.data.Message;
import com.application.chatsimple.data.MessageOutput;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.application.chatsimple.service.MessageFormatter.MESSAGE_TIME_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

public class MessageFormatterTest {

    private MessageFormatter messageFormatter;

    @Before
    public void setup(){
        messageFormatter = new MessageFormatter();
    }

    @Test
    public void shouldCreateMessageWithFormattedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(MESSAGE_TIME_FORMAT);
        String time = sdf.format(new Date());
        Message message = new Message("Marcelo", "Hello, my name is Marcelo.");

        MessageOutput messageOutput = messageFormatter.format(message);

        assertThat(messageOutput.getDateTime()).isEqualTo(time);
    }

}