package com.example.ch2labs.labs04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {
    @GetMapping("/rps")
    public Map<String, String> game(String user) {
        Map<Integer, String> rule = new HashMap<>();
        rule.put(0, "scissors");
        rule.put(1, "rock");
        rule.put(2, "paper");

        int computer_choice = (int) Math.floor(Math.random() * 3);
        String server = rule.get(computer_choice);

        String result = "";
        if (user.equals(server)) {
            result = "You Draw!";
        } else {
            if (user.equals("scissors")) {
                if (server.equals("rock")) {
                    result = "You Lose!";
                } else if (server.equals("paper")) {
                    result = "You Win!";
                }
            } else if (user.equals("rock")) {
                if (server.equals("paper")) {
                    result = "You Lose!";
                }  else if (server.equals("scissors")) {
                    result = "You Win!";
                }
            } else if (user.equals("paper")) {
                if (server.equals("scissors")) {
                    result = "You Lose!";
                } else if (server.equals("rock")) {
                    result = "You Win!";
                }
            } else {
                result = "scissors, rock, paper 중에 선택해야 합니다!";
            }
        }

        Map<String, String> res = new HashMap<>();
        res.put("user", user);
        res.put("server", server);
        res.put("result", result);

        return res;
    }
}
