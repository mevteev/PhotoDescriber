package com.jewtiejew.photodescriber.webservice.service.text;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriberImpl implements Describer {

    private static float THRESHOLD = 80F;

    @Override
    public String describeLabels(List<Label> labels) {
        StringBuilder text = new StringBuilder("I see ");
        if (labels.isEmpty()) {
            text.append("nothing");
        } else {
            for (Label label : labels) {
                /*if (label.getName().equals("Person")) {  // todo: ?????
                    return "";
                }*/
                text.append(label.getName());
                text.append(", ");
            }
            text.setLength(text.length() - 2);
        }
        text.append(".");

        return text.toString();
    }

    @Override
    public String describeFace(FaceDetail faceDetail) {
        StringBuilder text = new StringBuilder();

        text.append("I see ");
        boolean isMan = faceDetail.getGender().getValue().equals("Male");
        text.append(isMan? "man" : "woman");
        text.append(", ");

        //age
        AgeRange ageRange = faceDetail.getAgeRange();
        text.append(sex(isMan));
        text.append("is between ");
        text.append(ageRange.getLow().toString());
        text.append(" and ");
        text.append(ageRange.getHigh().toString());
        text.append(" years old, ");

        // smile
        if (Boolean.TRUE.equals(faceDetail.getSmile().getValue())) {
            text.append(sex(isMan));
            text.append("is smiling, ");
        }

        // eyeglasses / sunglasses
        if (Boolean.TRUE.equals(faceDetail.getEyeglasses().getValue())) {
            text.append(sex(isMan));
            if (Boolean.TRUE.equals(faceDetail.getSunglasses().getValue())) {
                text.append("is wearing sunglasses, ");
            } else {
                text.append("is wearing eyeglasses, ");
            }
        }

        // beard
        if (Boolean.TRUE.equals(faceDetail.getBeard().getValue())) {
            text.append(sex(isMan));
            text.append("have beard, ");
        }

        // mustache
        if (Boolean.TRUE.equals(faceDetail.getMustache().getValue())) {
            text.append(sex(isMan));
            text.append("have mustache, ");
        }

        // emotions
        List<Emotion> emotions = faceDetail.getEmotions();

        for (Emotion emotion : emotions) {
            if (emotion.getConfidence() > THRESHOLD) {
                text.append(sex(isMan));
                text.append("is ");
                text.append(emotion.getType().toLowerCase());
                text.append(", ");
            }
        }

        return text.toString();
    }

    @Override
    public String describeCelebs(List<Celebrity> celebrities) {
        StringBuilder text = new StringBuilder();
        for (Celebrity celebrity : celebrities) {
            text.append("I see ");
            text.append(celebrity.getName());
            text.append(".\n");
        }
        return text.toString();
    }

    private String sex(boolean isMan) {
        return isMan ? "He " : "She ";
    }
}
