package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.text.Describer;
import com.jewtiejew.photodescriber.webservice.vo.DescribeImageRequest;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import org.springframework.stereotype.Service;

@Service
public class DescribeImage implements Processor<DescribeImageRequest, Response> {

    private final Describer describer;

    public DescribeImage(Describer describer) {
        this.describer = describer;
    }

    @Override
    public Response process(DescribeImageRequest request) {
        StringBuilder text = new StringBuilder();

        text.append(describer.describeLabels(request.getLabels()));

        request.getFaceDetails().forEach(
                (fd) -> text.append(describer.describeFace(fd))
        );

        text.append(describer.describeCelebs(request.getCelebrities()));
        return new Response(text.toString());
    }
}
