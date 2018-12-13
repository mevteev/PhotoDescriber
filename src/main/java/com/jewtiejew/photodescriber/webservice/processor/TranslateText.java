package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.aws.Translator;
import com.jewtiejew.photodescriber.webservice.vo.Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import com.jewtiejew.photodescriber.webservice.vo.TranslateRequest;
import org.springframework.stereotype.Service;

@Service
public class TranslateText implements Processor {

    private final Translator translator;

    public TranslateText(Translator translator) {
        this.translator = translator;
    }

    @Override
    public Response process(Request request) {

        if (request instanceof TranslateRequest) {
            TranslateRequest rq = (TranslateRequest) request;
            return new Response(translator.translate(rq.getText(), rq.getSourceLanguage(), rq.getDestLanguage()));
        } else {
            throw new IllegalArgumentException("Wrong request");
        }
    }
}
