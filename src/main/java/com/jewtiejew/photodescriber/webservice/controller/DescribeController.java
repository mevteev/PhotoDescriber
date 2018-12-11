package com.jewtiejew.photodescriber.webservice.controller;

import com.jewtiejew.photodescriber.webservice.processor.UploadFileToS3;
import com.jewtiejew.photodescriber.webservice.service.aws.S3Manager;
import com.jewtiejew.photodescriber.webservice.service.aws.S3ManagerImpl;
import com.jewtiejew.photodescriber.webservice.vo.InputStreamS3Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
public class DescribeController {

    @RequestMapping(value = "/describe", method = RequestMethod.POST)
    public Response describe(@RequestParam("file") MultipartFile stream) throws IOException {
        S3Manager s3Manager = new S3ManagerImpl();
        UploadFileToS3 uploadFileToS3 = new UploadFileToS3(s3Manager);

        InputStreamS3Request request = new InputStreamS3Request();
        request.setStream(stream.getInputStream());
        request.setBucket("photo-describer-bucket");
        request.setKey(String.valueOf(new Date().getTime()) + ".jpg");
        uploadFileToS3.process(request);

        return new Response(request.getKey());
    }
}
