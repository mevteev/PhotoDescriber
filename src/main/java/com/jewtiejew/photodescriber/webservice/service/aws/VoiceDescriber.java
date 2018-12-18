package com.jewtiejew.photodescriber.webservice.service.aws;

import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.VoiceId;

import java.io.InputStream;

public interface VoiceDescriber {

    InputStream describe(String text, VoiceId voiceId, OutputFormat outputFormat);
}
