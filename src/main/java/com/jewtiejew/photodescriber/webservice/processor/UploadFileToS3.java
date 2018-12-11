package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.aws.S3Manager;
import com.jewtiejew.photodescriber.webservice.vo.InputStreamS3Request;
import com.jewtiejew.photodescriber.webservice.vo.Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFileToS3 implements Processor {

    private final S3Manager manager;

    public UploadFileToS3(S3Manager manager) {
        this.manager = manager;
    }

    Logger logger = LoggerFactory.getLogger(UploadFileToS3.class);

    @Override
    public Response process(Request request) {

        if (request instanceof InputStreamS3Request) {
            InputStreamS3Request rq = (InputStreamS3Request)request;
            return manager.uploadImage(rq.getStream(), rq.getBucket(), rq.getKey());
        } else {
            throw new IllegalArgumentException("Wrong request");
        }
    }
}
