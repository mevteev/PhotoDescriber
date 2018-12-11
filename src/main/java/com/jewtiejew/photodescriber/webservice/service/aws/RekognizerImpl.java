package com.jewtiejew.photodescriber.webservice.service.aws;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class RekognizerImpl implements Rekognizer {

    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
    Logger logger = LoggerFactory.getLogger(RekognizerImpl.class);

    @Override
    public List<Label> getLabels(String bucket, String key) {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image()
                    .withS3Object(new S3Object()
                        .withBucket(bucket)
                        .withName(key)))
                    .withMaxLabels(10)
                    .withMinConfidence(75F);

        try {
            logger.info(String.format("Detect labels in %s from S3 bucket %s...", key, bucket));

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            logger.info(String.format("Detected labels in %s from S3 bucket %s...", key, bucket));
            return result.getLabels();
        } catch (AmazonRekognitionException e) {
            logger.error(e.getMessage());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<FaceDetail> getFaceDetails(String bucket, String key) {
        return null;
    }

    @Override
    public List<Celebrity> getCelebs(String bucket, String key) {
        return null;
    }
}
