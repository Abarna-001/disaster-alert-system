package com.Disaster.disaster_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements
        WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(
            StompEndpointRegistry registry) {
        // This is the URL frontend connects to
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(
            MessageBrokerRegistry config) {
        // Frontend subscribes to /topic/...
        config.enableSimpleBroker("/topic");
        // Frontend sends messages to /app/...
        config.setApplicationDestinationPrefixes("/app");
    }
}
