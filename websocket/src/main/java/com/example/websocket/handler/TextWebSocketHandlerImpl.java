package com.example.websocket.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TextWebSocketHandlerImpl extends TextWebSocketHandler {

    private ScheduledExecutorService scheduler;

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        System.out.println("Cliente conectado: " + session.getId());

        // Inicia o agendador para enviar mensagens periodicamente
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage("Mensagem do servidor a cada 5 segundos"));
                } else {
                    scheduler.shutdown(); // Encerra o agendador caso a conexão esteja fechada
                }
            } catch (IOException e) {
                e.printStackTrace();
                scheduler.shutdown();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws IOException {
        System.out.println("Mensagem recebida: " + message.getPayload());
        session.sendMessage(new TextMessage("Servidor recebeu: " + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        System.out.println("Conexão encerrada: " + session.getId());
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
}
