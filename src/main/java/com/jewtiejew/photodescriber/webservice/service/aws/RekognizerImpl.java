package com.jewtiejew.photodescriber.webservice.service.aws;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RekognizerImpl implements Rekognizer {

    private AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
    private Logger logger = LoggerFactory.getLogger(RekognizerImpl.class);

    @Override
    public List<Label> getLabels(String bucket, String key) {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(getImage(bucket, key))
                    .withMaxLabels(5)
                    .withMinConfidence(75F);

        try {
            logger.info(String.format("Detect labels in %s from S3 bucket %s...", key, bucket));

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            logger.info(String.format("Detected labels in %s from S3 bucket %s...", key, bucket));
            return result.getLabels();
        } catch (AmazonRekognitionException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<FaceDetail> getFaceDetails(String bucket, String key) {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(getImage(bucket, key))
                .withAttributes(Attribute.ALL);

        try {
            logger.info(String.format("Detect face in %s from S3 bucket %s...", key, bucket));
            DetectFacesResult result = rekognitionClient.detectFaces(request);
            logger.info(String.format("Detected face in %s from S3 bucket %s...", key, bucket));
            return result.getFaceDetails();
        } catch (AmazonRekognitionException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Celebrity> getCelebs(String bucket, String key) {
        RecognizeCelebritiesRequest request = new RecognizeCelebritiesRequest()
                .withImage(getImage(bucket, key));

        try {
            logger.info(String.format("Detect celebs in %s from S3 bucket %s...", key, bucket));
            RecognizeCelebritiesResult result = rekognitionClient.recognizeCelebrities(request);
            logger.info(String.format("Detected celebs in %s from S3 bucket %s...", key, bucket));
            return result.getCelebrityFaces();
        } catch (AmazonRekognitionException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private Image getImage(String bucket, String key) {
        return new Image()
                .withS3Object(new S3Object()
                        .withBucket(bucket)
                        .withName(key));
    }
}
