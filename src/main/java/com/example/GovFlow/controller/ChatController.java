package com.example.GovFlow.controller;

import com.example.GovFlow.model.ChatRequest;
import com.example.GovFlow.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(agentService.handleChat(request));
    }
}