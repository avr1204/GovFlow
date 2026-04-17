package com.example.GovFlow.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String callGemini(String prompt) {
        try {
            Client client = Client.builder()
                    .apiKey(apiKey)
                    .build();

            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.5-flash",
                    prompt,
                    null
            );

            if (response == null || response.text() == null || response.text().isBlank()) {
                return "No response from Gemini.";
            }

            return response.text();

        } catch (Exception e) {
            return "Gemini API error: " + e.getMessage();
        }
    }
}