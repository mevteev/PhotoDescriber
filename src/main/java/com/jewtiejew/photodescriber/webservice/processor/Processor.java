package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.vo.Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;

public interface Processor {

    Response process(Request request);
}
