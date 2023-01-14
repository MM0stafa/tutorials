package com.example.clientservice.service;

import com.example.clientservice.util.SocketUtil;
import com.example.clientservice.websocketclient.SessionHandlerSrv1;
import com.example.clientservice.websocketclient.SessionHandlerSrv2;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.CountDownLatch;

@Service
public class CallWebSockets {
    public void callWebSocketsWebServices(){
        SocketUtil.LATCH_COUNT = new CountDownLatch(2);
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new StringMessageConverter());
        StompSessionHandler sessionHandler1 = new SessionHandlerSrv1();
        StompSessionHandler sessionHandler2 = new SessionHandlerSrv2();
        stompClient.connect("ws://127.0.0.1:" + SocketUtil.SRV1_PORT+ "/" + SocketUtil.SOCKET_ENDPOINT, sessionHandler1);
        stompClient.connect("ws://127.0.0.1:" + SocketUtil.SRV2_PORT+ "/" + SocketUtil.SOCKET_ENDPOINT, sessionHandler2);
    }
}
