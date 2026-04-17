package com.example.GovFlow.controller;

import com.example.GovFlow.model.UserRequest;
import com.example.GovFlow.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecommendationController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/recommend")
    public ResponseEntity<String> recommend(@RequestBody UserRequest request) {
        String result = agentService.handleRecommendation(request);
        return ResponseEntity.ok(result);
    }
}