package com.jewtiejew.whatisee.webservice.controller;

import com.jewtiejew.whatisee.webservice.vo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class DescribeController {

    @RequestMapping(value = "/describe", method = RequestMethod.POST)
    public Response describe(@RequestParam(value = "image", required = false) InputStream stream) {
        return new Response("This is a text");
    }
}
