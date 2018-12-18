package com.jewtiejew.photodescriber.webservice.service.aws;

import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.*;
import com.jewtiejew.photodescriber.webservice.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class VoiceDescriberImpl implements VoiceDescriber {

    private final AmazonPolly client = AmazonPollyClientBuilder.defaultClient();
    Logger logger = LoggerFactory.getLogger(RekognizerImpl.class);

    @Override
    public InputStream describe(String text, VoiceId voiceId, OutputFormat outputFormat) {
        try {
            SynthesizeSpeechRequest request = new SynthesizeSpeechRequest()
                    .withText(text)
                    .withVoiceId(voiceId)
                    .withOutputFormat(outputFormat);

            SynthesizeSpeechResult result = client.synthesizeSpeech(request);

            return result.getAudioStream();
        } catch (AmazonPollyException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
