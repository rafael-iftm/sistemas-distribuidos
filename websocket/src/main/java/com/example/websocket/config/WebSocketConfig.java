package com.example.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.websocket.handler.TextWebSocketHandlerImpl;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        // Configura o endpoint WebSocket
        registry.addHandler(new TextWebSocketHandlerImpl(), "/ws")
                .setAllowedOrigins("*"); // Permite todas as origens (para fins didáticos)
    }
}
