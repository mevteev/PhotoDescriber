package com.jewtiejew.photodescriber.webservice.service.aws;

import com.jewtiejew.photodescriber.webservice.vo.Response;

import java.io.InputStream;

public interface S3Manager {

    Response uploadImage(InputStream stream, String bucket, String key);
    void deleteImage();
}
