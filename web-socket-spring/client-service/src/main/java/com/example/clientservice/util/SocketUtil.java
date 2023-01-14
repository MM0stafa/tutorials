package com.example.clientservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class SocketUtil {

    public static CountDownLatch LATCH_COUNT = new CountDownLatch(0);
    public static Map<String, Integer> COMMON_WORDS = new HashMap<>();
    public static final String SRV1_PORT = "9091";
    public static final String SRV2_PORT = "9092";
    public static final String SOCKET_ENDPOINT = "testEndpoint";

    public static synchronized void handleResponse(Object payload){
        HashMap<String,Integer> resultMap;
        try {
            resultMap = new ObjectMapper().readValue(payload.toString(), HashMap.class);
            for(Map.Entry<String, Integer> result : resultMap.entrySet()){
                if(SocketUtil.COMMON_WORDS.get(result.getKey()) == null)
                    SocketUtil.COMMON_WORDS.put(result.getKey(), result.getValue());
                else
                    if(result.getValue() > SocketUtil.COMMON_WORDS.get(result.getKey()))
                        SocketUtil.COMMON_WORDS.put(result.getKey(), result.getValue());
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            SocketUtil.LATCH_COUNT.countDown();
            System.out.println(SocketUtil.COMMON_WORDS);
        }
    }
}