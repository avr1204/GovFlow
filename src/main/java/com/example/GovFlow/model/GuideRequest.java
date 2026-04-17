package com.example.GovFlow.model;

public class GuideRequest {

    private String aidName;
    private String language;

    public GuideRequest() {
    }

    public String getAidName() {
        return aidName;
    }

    public void setAidName(String aidName) {
        this.aidName = aidName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}