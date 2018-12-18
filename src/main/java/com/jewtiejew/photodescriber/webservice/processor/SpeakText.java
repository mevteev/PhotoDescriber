package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.service.aws.VoiceDescriber;
import com.jewtiejew.photodescriber.webservice.vo.DescribeVoiceRequest;
import com.jewtiejew.photodescriber.webservice.vo.InputStreamResponse;
import org.springframework.stereotype.Service;

@Service
public class SpeakText implements Processor<DescribeVoiceRequest, InputStreamResponse> {

    private final VoiceDescriber voiceDescriber;

    public SpeakText(VoiceDescriber voiceDescriber) {
        this.voiceDescriber = voiceDescriber;
    }

    @Override
    public InputStreamResponse process(DescribeVoiceRequest request) {
        return new InputStreamResponse(voiceDescriber.describe(request.getText(),
                request.getVoiceId(), request.getOutputFormat()));
    }
}
