package com.example.clientservice.websocketclient;

import com.example.clientservice.service.CallWebSockets;
import com.example.clientservice.util.SocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class SessionHandlerSrv1 extends StompSessionHandlerAdapter {

    @Autowired
    CallWebSockets callWebSockets;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/fileTask", this); // subscribe channel
        session.send("/app/findCommonWords", "dracula"); // send request
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        SocketUtil.handleResponse(payload);
    }
}