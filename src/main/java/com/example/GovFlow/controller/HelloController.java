package com.example.GovFlow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/health")
    public String home() {
        return "GovFlow is running!";
    }
}