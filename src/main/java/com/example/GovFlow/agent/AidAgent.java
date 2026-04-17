package com.example.GovFlow.agent;

import com.example.GovFlow.model.UserRequest;
import com.example.GovFlow.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AidAgent {

    @Autowired
    private GeminiService geminiService;

    public String process(UserRequest user) {
        String prompt = """
                You are GovFlow AI, a Malaysian government aid assistant.
                Based on the user's details below, suggest suitable government aid or support programs.
                Keep the answer simple, practical, and easy to understand.

                User details:
                Age: %d
                Monthly Income: %.2f
                Employment Status: %s

                Please:
                1. Suggest likely aid programs
                2. Briefly explain why the user may qualify
                3. Keep the response concise
                """.formatted(
                user.getAge(),
                user.getIncome(),
                user.getEmploymentStatus()
        );

        return geminiService.callGemini(prompt);
    }
}