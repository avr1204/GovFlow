package com.example.GovFlow.agent;

import com.example.GovFlow.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageAgent {

    @Autowired
    private GeminiService geminiService;

    public String translateAndSimplify(String text, String language) {
        if (language == null || language.isBlank()) {
            return text;
        }

        String prompt = """
                Translate and simplify the following text into %s.
                Make it clear, natural, and easy for ordinary citizens to understand.

                Text:
                %s
                """.formatted(language, text);

        return geminiService.callGemini(prompt);
    }
}