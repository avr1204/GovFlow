package com.example.GovFlow.agent;

import com.example.GovFlow.model.GuideRequest;
import com.example.GovFlow.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideAgent {

    @Autowired
    private GeminiService geminiService;

    public String process(GuideRequest request) {
        String prompt = """
                You are GovFlow AI, a Malaysian government aid guide assistant.

                Explain clearly in %s how to apply for this government aid:
                %s

                Requirements:
                1. Use simple language
                2. Give step-by-step instructions
                3. Mention common required documents if relevant
                4. Keep it practical and citizen-friendly
                """.formatted(request.getLanguage(), request.getAidName());

        return geminiService.callGemini(prompt);
    }
}