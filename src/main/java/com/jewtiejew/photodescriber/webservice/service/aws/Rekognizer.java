package com.jewtiejew.photodescriber.webservice.service.aws;

import com.amazonaws.services.rekognition.model.Celebrity;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Label;

import java.util.List;

public interface Rekognizer {

    List<Label> getLabels(String bucket, String key);
    List<FaceDetail> getFaceDetails(String bucket, String key);
    List<Celebrity> getCelebs(String bucket, String key);
}
