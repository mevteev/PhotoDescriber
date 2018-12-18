package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.aws.Rekognizer;
import com.jewtiejew.photodescriber.webservice.vo.ImageAttributesResponse;
import com.jewtiejew.photodescriber.webservice.vo.S3Request;
import org.springframework.stereotype.Service;

@Service
public class RecognizeImage implements Processor<S3Request, ImageAttributesResponse> {

    private final Rekognizer rekognizer;

    public RecognizeImage(Rekognizer rekognizer) {
        this.rekognizer = rekognizer;
    }

    @Override
    public ImageAttributesResponse process(S3Request request) {
        return new ImageAttributesResponse(
                rekognizer.getLabels(request.getBucket(), request.getKey()),
                rekognizer.getFaceDetails(request.getBucket(), request.getKey()),
                rekognizer.getCelebs(request.getBucket(), request.getKey()));
    }
}
