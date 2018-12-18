package com.jewtiejew.photodescriber.webservice.processor;

import com.jewtiejew.photodescriber.webservice.vo.Request;
import com.jewtiejew.photodescriber.webservice.vo.Response;

public interface Processor<Rq extends Request, Rs extends Response> {

    Rs process(Rq rq);
}
