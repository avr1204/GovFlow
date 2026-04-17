package com.example.GovFlow.controller;

import com.example.GovFlow.model.GuideRequest;
import com.example.GovFlow.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GuideController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/guide")
    public String guide(@RequestBody GuideRequest request) {
        return agentService.handleGuide(request);
    }
}