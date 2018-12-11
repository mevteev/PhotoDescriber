package com.jewtiejew.photodescriber.webservice.vo;

public class S3Request extends Request{

    private String key;
    private String bucket;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
