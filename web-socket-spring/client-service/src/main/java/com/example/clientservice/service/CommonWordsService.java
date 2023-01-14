package com.example.clientservice.service;

import com.example.clientservice.util.SocketUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommonWordsService {

    private final CallWebSockets callWebSockets;

    public CommonWordsService(CallWebSockets callWebSockets) {
        this.callWebSockets = callWebSockets;
    }

    public List<Map.Entry> getCommonWords() {

        List<Map.Entry> commonWordsList = null;
        callWebSockets.callWebSocketsWebServices();

        try {
            SocketUtil.LATCH_COUNT.await();
            commonWordsList = SocketUtil.COMMON_WORDS.entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue().toString().compareTo(o1.getValue().toString()))
                    .limit(5).collect(Collectors.toList());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return commonWordsList;
    }


}
