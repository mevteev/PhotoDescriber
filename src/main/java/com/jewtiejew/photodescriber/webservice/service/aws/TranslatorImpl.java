package com.jewtiejew.photodescriber.webservice.service.aws;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.amazonaws.services.translate.model.AmazonTranslateException;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TranslatorImpl implements Translator {

    AmazonTranslate client = AmazonTranslateClientBuilder.defaultClient();
    Logger logger = LoggerFactory.getLogger(RekognizerImpl.class);


    @Override
    public String translate(String text, String from, String to) {

        TranslateTextRequest request = new TranslateTextRequest()
                .withText(text)
                .withSourceLanguageCode(from)
                .withTargetLanguageCode(to);

        try {
            logger.info("Translate text {}", request.getText());
            TranslateTextResult result = client.translateText(request);
            logger.info("Translated : {}", result.getTranslatedText());
            return result.getTranslatedText();
        } catch (AmazonTranslateException e) {
            logger.error(e.getMessage());
        }
        return "Error";
    }
}
