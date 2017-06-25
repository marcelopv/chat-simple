package com.application.chatsimple.controller;

import com.application.chatsimple.data.Message;
import com.application.chatsimple.service.ChatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChatControllerUnitTest {

    @InjectMocks
    private ChatController controller;

    @Mock
    private ChatService service;

    @Test
    public void shouldDelegateMessageToService(){
        Message johnMessage = new Message("John", "Hey!");
        controller.sendMessage(johnMessage);

        verify(service).sendMessage(johnMessage);
    }

}