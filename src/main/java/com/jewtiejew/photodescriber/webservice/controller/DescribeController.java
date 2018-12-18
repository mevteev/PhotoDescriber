package com.jewtiejew.photodescriber.webservice.controller;

import com.amazonaws.util.IOUtils;
import com.jewtiejew.photodescriber.webservice.processor.*;
import com.jewtiejew.photodescriber.webservice.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
public class DescribeController {

    @Autowired
    private UploadFileToS3 uploadFileToS3;

    @Autowired
    private RecognizeImage recognizeImage;

    @Autowired
    private DescribeImage describeImage;

    @Autowired
    private TranslateText translateText;

    @Autowired
    private SpeakText speakText;

    public static final String BUCKET = "photo-describer-bucket";

    @RequestMapping(value = "/describe", method = RequestMethod.GET)
    public void describe(@RequestParam("file") MultipartFile stream, HttpServletResponse response) throws IOException {

        InputStreamS3Request request = new InputStreamS3Request();
        request.setStream(stream.getInputStream());
        request.setBucket(BUCKET);
        request.setKey(String.valueOf(new Date().getTime()) + ".jpg");
        uploadFileToS3.process(request);

        S3Request s3Request = new S3Request();
        s3Request.setKey(request.getKey());
        s3Request.setBucket(BUCKET);

        Response recognitionResult = recognizeImage.process(s3Request);

        DescribeImageRequest describeImageRequest = new DescribeImageRequest(
                ((ImageAttributesResponse) recognitionResult).getLabels(),
                ((ImageAttributesResponse) recognitionResult).getFaceDetails(),
                ((ImageAttributesResponse) recognitionResult).getCelebrities());

        Response describeImageResult = describeImage.process(describeImageRequest);

        Response translatedText = translateText.process(new TranslateRequest(describeImageResult.getText(),
                "en", "ru"));

        InputStream voiceStream = speakText.process(new DescribeVoiceRequest(translatedText.getText())).getStream();

        response.addHeader("Content-disposition", "attachment;filename=speech.mp3");
        response.setContentType("audio/mpeg");

        IOUtils.copy(voiceStream, response.getOutputStream());
        response.flushBuffer();
    }
}
