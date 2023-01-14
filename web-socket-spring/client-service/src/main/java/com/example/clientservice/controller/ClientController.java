package com.example.clientservice.controller;

import com.example.clientservice.service.CommonWordsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final CommonWordsService commonWordsService;

    public ClientController(CommonWordsService commonWordsService) {
        this.commonWordsService = commonWordsService;
    }

    @GetMapping("/commonWords")
    public List<Map.Entry> getCommonWords(){
            return  commonWordsService.getCommonWords();
    }

}
