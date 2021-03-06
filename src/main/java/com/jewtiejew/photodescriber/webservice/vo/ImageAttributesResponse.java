package com.jewtiejew.photodescriber.webservice.vo;

import com.amazonaws.services.rekognition.model.Celebrity;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Label;

import java.util.List;

public class ImageAttributesResponse extends Response {

    private final List<Label> labels;
    private final List<FaceDetail> faceDetails;
    private final List<Celebrity> celebrities;

    public ImageAttributesResponse(List<Label> labels, List<FaceDetail> faceDetails, List<Celebrity> celebrities) {
        super(labels.toString() + "\n" + faceDetails.toString() + "\n" + celebrities.toString());
        this.labels = labels;
        this.faceDetails = faceDetails;
        this.celebrities = celebrities;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public List<FaceDetail> getFaceDetails() {
        return faceDetails;
    }

    public List<Celebrity> getCelebrities() {
        return celebrities;
    }
}
