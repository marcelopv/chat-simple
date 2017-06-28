package com.application.chatsimple.config;

import com.application.chatsimple.service.ChatService;
import com.application.chatsimple.service.MessageFormatter;
import com.application.chatsimple.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ChatService chatService(){
        return new ChatService(messageFormatter());
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    public MessageFormatter messageFormatter(){
        return new MessageFormatter();
    }

}
