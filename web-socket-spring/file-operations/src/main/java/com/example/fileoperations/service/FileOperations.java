package com.example.fileoperations.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileOperations {

    public List<Map.Entry> findTopCommon(String fileName){
        File file = new File(this.getClass().getResource("/templates/"+ fileName +".txt").getPath());
        BufferedReader bufferedReader = null;
        String inputLine = null;
        Map<String, Integer> wordsMap = new HashMap<>();
        List<Map.Entry>  result = new ArrayList<>();
		try {
                bufferedReader = new BufferedReader(new FileReader(file));
                while ((inputLine = bufferedReader.readLine()) != null) {
                    String[] words = inputLine.split(" ");
                    for (String word : words) {
                        String key = word.toLowerCase();
                        if (key.length() > 0) {
                            if (wordsMap.get(key) == null) {
                                wordsMap.put(key, 1);
                            } else {
                                wordsMap.put(key, wordsMap.get(key)+1);
                            }
                        }
                    }
                }

                result = wordsMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue((Comparator.reverseOrder())))
                    .limit(5)
                    .collect(Collectors.toList());

        } catch (IOException error) {
            error.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
}
