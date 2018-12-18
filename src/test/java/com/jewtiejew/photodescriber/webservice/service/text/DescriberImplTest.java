package com.jewtiejew.photodescriber.webservice.service.text;

import org.junit.Test;

public class DescriberImplTest {

    private DescriberImpl describer = new DescriberImpl();

    @Test
    public void replaceLastCommaTest() {
        String text = "I see book, button, phone, car, computer";
        String expected = "I see book, button, phone, car and computer";

        assert expected.equals(describer.replaceLastComma(new StringBuilder(text)).toString());
    }
}
