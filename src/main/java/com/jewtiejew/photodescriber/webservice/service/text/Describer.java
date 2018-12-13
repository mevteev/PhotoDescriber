package com.jewtiejew.photodescriber.webservice.service.text;

import com.amazonaws.services.rekognition.model.Celebrity;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Label;

import java.util.List;

public interface Describer {

    String describeLabels(List<Label> labels);
    String describeFace(FaceDetail faceDetail);
    String describeCelebs(List<Celebrity> celebrities);
}
