package com.application.chatsimple.controller;

import com.application.chatsimple.Application;
import com.application.chatsimple.config.ApplicationConfigTest;
import com.application.chatsimple.data.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = {Application.class, ApplicationConfigTest.class})
public class ChatControllerIntegrationTest {

    private static final String TOPIC_MESSAGES = "/topic/messages";
    private static final String ENDPOINT_SEND_MESSAGE = "/app/send";
    private static String URL;

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private WebSocketStompClient stompClient;

    private CompletableFuture<Message> completableFuture = new CompletableFuture<>();

    @Before
    public void init(){
        URL = "ws://localhost:"+port+"/chat-simple";
    }

    @Test
    public void givenTopicMessages_whenSendMessage_thenBroadcastMessageToListeners() throws InterruptedException, ExecutionException, TimeoutException {
        StompSession stompSession = stompClient.connect(URL, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);

        stompSession.subscribe(TOPIC_MESSAGES, new CreateStompFrameHandler());

        Message message = new Message("Marcelo", "Hello!");
        stompSession.send(ENDPOINT_SEND_MESSAGE, message);

        Message messageResponse = completableFuture.get(10, SECONDS);
        assertThat(messageResponse).isEqualTo(message);
    }

    private class CreateStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return Message.class;
        }
        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            completableFuture.complete((Message) o);
        }
    }
}
