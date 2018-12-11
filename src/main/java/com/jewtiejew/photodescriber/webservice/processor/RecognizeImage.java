package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.aws.Rekognizer;
import com.jewtiejew.photodescriber.webservice.vo.ImageAttributesResponse;
import com.jewtiejew.photodescriber.webservice.vo.Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import com.jewtiejew.photodescriber.webservice.vo.S3Request;

import java.util.Collections;

public class RecognizeImage implements Processor {

    private final Rekognizer rekognizer;

    public RecognizeImage(Rekognizer rekognizer) {
        this.rekognizer = rekognizer;
    }

    @Override
    public Response process(Request request) {

        if (request instanceof S3Request) {
            S3Request rq = (S3Request) request;
            return new ImageAttributesResponse(
                    rekognizer.getLabels(rq.getBucket(), rq.getKey()),
                    Collections.EMPTY_LIST,
                    Collections.EMPTY_LIST);
        } else {
            throw new IllegalArgumentException("Wrong request");
        }
    }
}
