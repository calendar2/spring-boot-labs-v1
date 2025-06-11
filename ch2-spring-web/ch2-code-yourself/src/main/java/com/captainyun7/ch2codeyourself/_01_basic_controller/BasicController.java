package com.captainyun7.ch2codeyourself._01_basic_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class BasicController {
    @GetMapping("/basic/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/basic/users/{userId}")
    @ResponseBody
    public String users(@PathVariable int userId) {
        return "user Id: " + userId;
    }

    @GetMapping("/basic/users/{userId}/orders/{orderId}")
    @ResponseBody
    public String userOrder(@PathVariable int userId, @PathVariable int orderId) {
        return "user Id: " + userId + ", order Id: " + orderId;
    }

    @GetMapping("/basic/params")
    @ResponseBody
    public String params(String name, int age) {
        return "name: " + name + ", age: " + age;
    }

    @GetMapping("/basic/filter")
    @ResponseBody
    public String params(@RequestParam Map<String, String> params) {
        return "전체: " + params.toString();
    }

    @ResponseBody
    @PostMapping("/basic/users")
    public String post() {
        return "사용자 생성 성공";
    }

    @ResponseBody
    @PutMapping("/basic/users/{userId}")
    public String put(@PathVariable int userId) {
        return userId + "사용자 수정 성공";
    }

    @ResponseBody
    @DeleteMapping("/basic/users/{userId}")
    public String delete(@PathVariable int userId) {
        return userId + "사용자 삭제 성공";
    }
}
