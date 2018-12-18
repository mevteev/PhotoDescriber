package com.jewtiejew.photodescriber.webservice.vo;

import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.VoiceId;

public class DescribeVoiceRequest extends Request {

    private String text;
    private VoiceId voiceId;
    private OutputFormat outputFormat;

    public DescribeVoiceRequest(String text, VoiceId voiceId, OutputFormat outputFormat) {
        this.text = text;
        this.voiceId = voiceId;
        this.outputFormat = outputFormat;
    }

    public DescribeVoiceRequest(String text) {
        this.text = text;
        this.voiceId = VoiceId.Maxim;
        this.outputFormat = OutputFormat.Mp3;
    }

    public String getText() {
        return text;
    }

    public VoiceId getVoiceId() {
        return voiceId;
    }

    public OutputFormat getOutputFormat() {
        return outputFormat;
    }
}
