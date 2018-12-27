package com.jewtiejew.photodescriber.webservice.service.text;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DescriberImplTest {

    private DescriberImpl describer = new DescriberImpl();

    @Test
    public void replaceLastCommaTest() {
        String text = "I see book, button, phone, car, computer";
        String expected = "I see book, button, phone, car and computer";

        assertEquals(expected,describer.replaceLastComma(new StringBuilder(text)).toString());
    }
}
