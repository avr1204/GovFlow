package com.example.GovFlow.service;

import com.example.GovFlow.agent.AidAgent;
import com.example.GovFlow.agent.GuideAgent;
import com.example.GovFlow.agent.LanguageAgent;
import com.example.GovFlow.model.ChatRequest;
import com.example.GovFlow.model.GuideRequest;
import com.example.GovFlow.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    private AidAgent aidAgent;

    @Autowired
    private LanguageAgent languageAgent;

    @Autowired
    private GuideAgent guideAgent;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private GeminiService geminiService;

    public String handleRecommendation(UserRequest request) {
        String result = aidAgent.process(request);

        if (isBadAiResult(result)) {
            result = recommendationService.getRecommendation(
                    request.getAge(),
                    request.getIncome(),
                    request.getEmploymentStatus()
            );
        }

        String translated = languageAgent.translateAndSimplify(result, request.getLanguage());

        if (isBadAiResult(translated)) {
            return result;
        }

        return translated;
    }

    public String handleChat(ChatRequest request) {
        String prompt = """
                You are GovFlow AI, a helpful Malaysian government aid assistant.
                Answer clearly, simply, and helpfully in %s.

                User question:
                %s
                """.formatted(
                request.getLanguage(),
                request.getMessage()
        );

        String result = geminiService.callGemini(prompt);

        if (isBadAiResult(result)) {
            return fallbackChatReply(request.getMessage(), request.getLanguage());
        }

        return result;
    }

    public String handleGuide(GuideRequest request) {
        String result = guideAgent.process(request);

        if (isBadAiResult(result)) {
            return fallbackGuideReply(request.getAidName(), request.getLanguage());
        }

        return result;
    }

    private boolean isBadAiResult(String text) {
        return text == null
                || text.isBlank()
                || text.startsWith("Gemini API error:")
                || text.equalsIgnoreCase("No response from Gemini.");
    }

    private String fallbackChatReply(String message, String language) {
        String lower = message == null ? "" : message.toLowerCase();
        String reply;

        if (lower.contains("aid") || lower.contains("bantuan") || lower.contains("help")) {
            reply = "You can use the Aid Finder tab to check possible government assistance based on your age, income, and employment status.";
        } else if (lower.contains("apply") || lower.contains("application")) {
            reply = "To apply, prepare your identification documents, check eligibility, complete the application form, and submit it through the official government portal.";
        } else if (lower.contains("document") || lower.contains("dokumen")) {
            reply = "Common documents may include identification card, income proof, and supporting household documents depending on the aid program.";
        } else if (lower.contains("eligible") || lower.contains("kelayakan")) {
            reply = "Eligibility usually depends on income level, age, employment status, and household background.";
        } else {
            reply = "I can help with government aid, eligibility, required documents, and application steps. Try asking something like: What aid can I apply for?";
        }

        return recommendationService.translateSimple(reply, language);
    }

    private String fallbackGuideReply(String aidName, String language) {
        String guide = """
                Application Guide for: %s

                1. Prepare your identification documents.
                2. Check your eligibility requirements.
                3. Fill in the official application form.
                4. Submit the form through the official government portal or counter.
                5. Wait for confirmation or further notice.
                """.formatted(aidName);

        return recommendationService.translateSimple(guide, language);
    }
}