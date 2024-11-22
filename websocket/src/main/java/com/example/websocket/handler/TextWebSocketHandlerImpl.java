package com.example.websocket.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.lang.NonNull;

import java.io.IOException;

public class TextWebSocketHandlerImpl extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws IOException {
        // Exibe a mensagem recebida
        System.out.println("Mensagem recebida: " + message.getPayload());

        // Envia uma resposta ao cliente
        session.sendMessage(new TextMessage("Servidor recebeu: " + message.getPayload()));
    }
}
