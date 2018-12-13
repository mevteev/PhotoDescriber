package com.jewtiejew.photodescriber.webservice.service.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.util.IOUtils;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class S3ManagerImpl implements S3Manager {

    Logger logger = LoggerFactory.getLogger(S3ManagerImpl.class);

    @Override
    public Response uploadImage(InputStream stream, String bucket, String key) {
        PutObjectRequest putObjectRequest = getPutObjectRequest(stream, bucket, key);
        if (putObjectRequest != null) {

            logger.info(String.format("Uploading %s to S3 bucket %s...", key, bucket));
            final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
            final TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3).build();
            try {
                Upload upload = tm.upload(putObjectRequest);

                upload.waitForCompletion();
                logger.info(String.format("Uploaded %s to S3 bucket %s...", key, bucket));
            } catch (AmazonServiceException | InterruptedException e) {
                logger.error(e.toString());
            }
        }
        return null;
    }

    private PutObjectRequest getPutObjectRequest(InputStream stream, String bucket, String key) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            byte[] bites = IOUtils.toByteArray(stream);
            metadata.setContentLength(bites.length);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bites);
            PutObjectRequest request = new PutObjectRequest(bucket, key, byteArrayInputStream, metadata);

            request.setGeneralProgressListener(
                    (event) -> logger.info("Transferred bytes: " + event.getBytesTransferred())
            );

            return request;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteImage() {

    }
}
