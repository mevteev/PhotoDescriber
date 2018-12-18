package com.jewtiejew.photodescriber.webservice.vo;

import java.io.InputStream;

public class InputStreamResponse extends Response {

    private InputStream stream;

    public InputStreamResponse(String text, InputStream stream) {
        super(text);
        this.stream = stream;
    }

    public InputStreamResponse(InputStream stream) {
        super("");
        this.stream = stream;
    }

    public InputStream getStream() {
        return stream;
    }
}
