package com.jewtiejew.photodescriber.webservice.service.aws;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.amazonaws.services.translate.model.AmazonTranslateException;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TranslatorImpl implements Translator {

    AmazonTranslate client = AmazonTranslateClientBuilder.defaultClient();
    Logger logger = LoggerFactory.getLogger(RekognizerImpl.class);


    @Override
    public String translate(String text, String from, String to) {
        return translate(text, from, to, null);
    }

    @Override
    public String translate(String text, String from, String to,
                            Function<String, String> postProcess) {

        TranslateTextRequest request = new TranslateTextRequest()
                .withText(text)
                .withSourceLanguageCode(from)
                .withTargetLanguageCode(to);

        try {
            logger.info("Translate text {}", request.getText());
            TranslateTextResult result = client.translateText(request);
            logger.info("Translated : {}", result.getTranslatedText());
            String translatedText = result.getTranslatedText();
            if (postProcess != null) {
                return postProcess.apply(translatedText);
            }
            return translatedText;
        } catch (AmazonTranslateException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
