package com.example.SpringLogin.Configrations;

import com.example.SpringLogin.Socket.WebSocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("web socket enboint handler ");
        registry.addHandler(new WebSocket(),"/videochat").setAllowedOrigins("*");
    }
}
