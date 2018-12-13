package com.jewtiejew.photodescriber.webservice.vo;

public class TranslateRequest extends Request {

    private String text;
    private String sourceLanguage;
    private String descLanguage;

    public TranslateRequest(String text, String sourceLanguage, String descLanguage) {
        this.text = text;
        this.sourceLanguage = sourceLanguage;
        this.descLanguage = descLanguage;
    }

    public String getText() {
        return text;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getDestLanguage() {
        return descLanguage;
    }
}
