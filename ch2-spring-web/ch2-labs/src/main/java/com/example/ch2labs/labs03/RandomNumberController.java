package com.example.ch2labs.labs03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RandomNumberController {
    @GetMapping("/random")
    public Map<String, Integer> randomNumber(int min, int max) {
        int range = max - min + 1;
        int number = (int) Math.floor(Math.random() * range) + min;
        Map<String, Integer> map = new HashMap<>();
        map.put("number", number);

        return map;
    }
}
