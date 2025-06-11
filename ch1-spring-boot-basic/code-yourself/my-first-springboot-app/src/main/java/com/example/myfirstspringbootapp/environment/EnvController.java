package com.example.myfirstspringbootapp.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
    @Autowired
    Environment environment;

    @GetMapping("/env")
    public String env() {
        return environment.getProperty("server.port");
    }
}
