package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.aws.S3Manager;
import com.jewtiejew.photodescriber.webservice.vo.InputStreamS3Request;
import com.jewtiejew.photodescriber.webservice.vo.Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UploadFileToS3 implements Processor<InputStreamS3Request, Response> {

    private final S3Manager manager;
    private Logger logger = LoggerFactory.getLogger(UploadFileToS3.class);

    public UploadFileToS3(S3Manager manager) {
        this.manager = manager;
    }


    @Override
    public Response process(InputStreamS3Request request) {
        return manager.uploadImage(request.getStream(), request.getBucket(), request.getKey());
    }
}
