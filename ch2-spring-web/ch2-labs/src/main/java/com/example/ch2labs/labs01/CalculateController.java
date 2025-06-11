package com.example.ch2labs.labs01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateController {
    @GetMapping("/calc")
    public String calculate(int x, int y, String op) {
        int result = 0;

        switch (op) {
            case "add":
                result = x + y;
                return "결과: " + x + " + " + y + " = " + result;
            case "sub":
                result = x - y;
                return "결과: " + x + " - " + y + " = " + result;
            case "mul":
                result = x * y;
                return "결과: " + x + " * " + y + " = " + result;
            case "div":
                result = x / y;
                return "결과: " + x + " / " + y + " = " + result;
        }

        return "op 값은 add, sub, mul, div 4가지만 가능합니다.";
    }
}
