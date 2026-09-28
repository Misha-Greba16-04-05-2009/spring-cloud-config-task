package ru.netology.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${app.message}")
    private String message;

    // GET localhost:8081/config/message
    @GetMapping("/config/message")
    public String getMessage() {
        return message;
    }
}
