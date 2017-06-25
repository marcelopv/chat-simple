package com.application.chatsimple.service;

import com.application.chatsimple.data.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChatServiceUnitTest {

    @InjectMocks
    private ChatService service;

    @Mock
    private MessageFormatter formatter;

    @Test
    public void shouldDelegateFormattingLogicWhenReceiveMessages(){
        Message johnMessage = new Message("John", "Hey!");

        service.sendMessage(johnMessage);

        verify(formatter).format(eq(johnMessage));
    }

}