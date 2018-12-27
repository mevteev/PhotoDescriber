package com.jewtiejew.photodescriber.webservice.service.aws;

import java.util.function.Function;

public interface Translator {

    String translate(String text, String from, String to);

    String translate(String text, String from, String to,
                     Function<String, String> postProcess);
}
