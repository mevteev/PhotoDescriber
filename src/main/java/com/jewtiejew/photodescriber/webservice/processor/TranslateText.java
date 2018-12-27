package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.aws.Translator;
import com.jewtiejew.photodescriber.webservice.service.text.TextUtils;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import com.jewtiejew.photodescriber.webservice.vo.TranslateRequest;
import org.springframework.stereotype.Service;

@Service
public class TranslateText implements Processor<TranslateRequest, Response> {

    private final Translator translator;

    public TranslateText(Translator translator) {
        this.translator = translator;
    }

    @Override
    public Response process(TranslateRequest request) {
        return new Response(translator.translate(request.getText(), request.getSourceLanguage(),
                        request.getDestLanguage(), TextUtils::removeDuplicatedWords));
    }
}
