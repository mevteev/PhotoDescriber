package com.jewtiejew.photodescriber.webservice.controller;

import com.amazonaws.services.rekognition.model.Label;
import com.jewtiejew.photodescriber.webservice.processor.RecognizeImage;
import com.jewtiejew.photodescriber.webservice.processor.UploadFileToS3;
import com.jewtiejew.photodescriber.webservice.service.aws.Rekognizer;
import com.jewtiejew.photodescriber.webservice.service.aws.RekognizerImpl;
import com.jewtiejew.photodescriber.webservice.service.aws.S3Manager;
import com.jewtiejew.photodescriber.webservice.service.aws.S3ManagerImpl;
import com.jewtiejew.photodescriber.webservice.vo.ImageAttributesResponse;
import com.jewtiejew.photodescriber.webservice.vo.InputStreamS3Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import com.jewtiejew.photodescriber.webservice.vo.S3Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
public class DescribeController {

    @Autowired
    private UploadFileToS3 uploadFileToS3;

    @Autowired
    private RecognizeImage recognizeImage;

    public static final String BUCKET = "photo-describer-bucket";

    @RequestMapping(value = "/describe", method = RequestMethod.POST)
    public Response describe(@RequestParam("file") MultipartFile stream) throws IOException {

        /*
        InputStreamS3Request request = new InputStreamS3Request();
        request.setStream(stream.getInputStream());
        request.setBucket(BUCKET);
        request.setKey(String.valueOf(new Date().getTime()) + ".jpg");
        uploadFileToS3.process(request);*/

        S3Request request = new S3Request();
        request.setKey("1544529103069.jpg");
        request.setBucket(BUCKET);

        return recognizeImage.process(request);
    }
}
