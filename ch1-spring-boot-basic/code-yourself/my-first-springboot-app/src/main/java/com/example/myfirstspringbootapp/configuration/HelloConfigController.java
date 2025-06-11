package com.example.myfirstspringbootapp.configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConfigController {
    private final HelloConfigBean helloConfigBean;

    public HelloConfigController(HelloConfigBean helloConfigBean) {
        this.helloConfigBean = helloConfigBean;
    }

    @GetMapping("/hello-bean")
    public String sayHello() {
        return helloConfigBean.sayHello();
    }
}
