package com.jewtiejew.photodescriber.webservice.vo;

import com.amazonaws.services.rekognition.model.Celebrity;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Label;

import java.util.List;

public class DescribeImageRequest extends Request {

    private List<Label> labels;
    private List<FaceDetail> faceDetail;
    private List<Celebrity> celebrities;

    public DescribeImageRequest(List<Label> labels, List<FaceDetail> faceDetail, List<Celebrity> celebrities) {
        this.labels = labels;
        this.faceDetail = faceDetail;
        this.celebrities = celebrities;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public List<FaceDetail> getFaceDetails() {
        return faceDetail;
    }

    public List<Celebrity> getCelebrities() {
        return celebrities;
    }
}
