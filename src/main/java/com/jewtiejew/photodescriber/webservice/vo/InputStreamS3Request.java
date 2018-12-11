package com.jewtiejew.photodescriber.webservice.vo;

import java.io.InputStream;

public class InputStreamS3Request extends S3Request {

    protected InputStream stream;

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }
}
