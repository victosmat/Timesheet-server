package com.timesheet.configuration.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.logging.Logger;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final Logger log = Logger.getLogger(WebSocketConfig.class.getName());
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("register stomp endpoints");
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:4200").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        log.info("configure message broker");
        registry.setApplicationDestinationPrefixes("/message");
        registry.enableSimpleBroker("/topic");
        registry.setUserDestinationPrefix("/employeeID");
    }
}
