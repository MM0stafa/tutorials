package com.example.fileoperations.controller;

import com.example.fileoperations.service.FileOperations;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class WebSocketController {

    final FileOperations fileOperations;

    public WebSocketController(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    @MessageMapping("/findCommonWords")
    @SendTo("/topic/fileTask")
    public String greeting(String fileName) throws Exception {
        JSONObject result = new JSONObject();
        for (Map.Entry<String, Integer> item : fileOperations.findTopCommon(fileName)) {
            result.put(item.getKey(), item.getValue());
        }
        return result.toString();
    }

}
