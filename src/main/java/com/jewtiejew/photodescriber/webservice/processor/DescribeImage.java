package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.text.Describer;
import com.jewtiejew.photodescriber.webservice.vo.DescribeImageRequest;
import com.jewtiejew.photodescriber.webservice.vo.Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import org.springframework.stereotype.Service;

@Service
public class DescribeImage implements Processor {

    private final Describer describer;

    public DescribeImage(Describer describer) {
        this.describer = describer;
    }

    @Override
    public Response process(Request request) {
        if (request instanceof DescribeImageRequest) {
            DescribeImageRequest rq = (DescribeImageRequest) request;

            StringBuilder text = new StringBuilder();

            text.append(describer.describeLabels(rq.getLabels()));

            rq.getFaceDetails().forEach(
                    (fd) -> text.append(describer.describeFace(fd))
            );

            text.append(describer.describeCelebs(rq.getCelebrities()));
            return new Response(text.toString());
        } else {
            throw new IllegalArgumentException("Wrong request");
        }
    }
}
