package com.jewtiejew.photodescriber.webservice.vo;

import java.io.Serializable;

public class Response implements Serializable {

    private final String text;

    public Response(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
