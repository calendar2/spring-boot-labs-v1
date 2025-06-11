package com.example.ch2labs.labs02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DiceController {
    @GetMapping("/dice")
    public Map<String, Integer> dice() {
        int dice_num = (int) Math.floor(Math.random() * 6) + 1;
        Map<String, Integer> dice_map = new HashMap<>();
        dice_map.put("dice", dice_num);

        return dice_map;
    }
}
