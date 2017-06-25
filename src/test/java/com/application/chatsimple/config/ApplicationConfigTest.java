package com.application.chatsimple.config;

import com.application.chatsimple.service.ChatService;
import com.application.chatsimple.service.MessageFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfigTest {

    @Bean
    public WebSocketStompClient stompClient(){
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return webSocketStompClient;
    }

    @Bean
    public ChatService chatService(){
        return new ChatService(messageFormatter());
    }

    public MessageFormatter messageFormatter(){
        return new MessageFormatter();
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }



}
