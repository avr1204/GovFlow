package com.example.GovFlow.service;

import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    public String getRecommendation(int age, double income, String employmentStatus) {
        String result;

        if (income <= 2000) {
            result = "You may qualify for low-income government assistance such as Bantuan Sara Hidup, household support schemes, or targeted welfare aid.";
        } else if (income <= 4000) {
            result = "You may qualify for middle-income support programs, cost-of-living assistance, or selected household subsidies.";
        } else {
            result = "Your income may place you outside the main low-income aid group, but you may still qualify for selected support programs depending on your household situation.";
        }

        if ("student".equalsIgnoreCase(employmentStatus)) {
            result += "\nYou may also check for student assistance, education support, or digital device aid.";
        } else if ("unemployed".equalsIgnoreCase(employmentStatus)) {
            result += "\nSince you are unemployed, you may also check for job-seeker assistance and emergency support programs.";
        } else if ("retired".equalsIgnoreCase(employmentStatus)) {
            result += "\nSince you are retired, you may also check for senior citizen welfare and healthcare support.";
        } else if ("self-employed".equalsIgnoreCase(employmentStatus)) {
            result += "\nSince you are self-employed, you may also check for micro-business or self-employment support schemes.";
        }

        if (age >= 60) {
            result += "\nBecause you are a senior citizen, you may also be eligible for elderly assistance schemes.";
        }

        return result;
    }

    public String translateSimple(String text, String language) {
        if (language == null || language.isBlank()) {
            return text;
        }

        switch (language.toLowerCase()) {
            case "bahasa melayu":
                return "Cadangan GovFlow:\n" + text;
            case "chinese":
                return "GovFlow 推荐结果：\n" + text;
            case "tamil":
                return "GovFlow பரிந்துரை முடிவு:\n" + text;
            default:
                return "GovFlow Recommendation:\n" + text;
        }
    }
}