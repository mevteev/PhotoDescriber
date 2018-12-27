package com.jewtiejew.photodescriber.webservice.service.text;


import java.util.*;

public class TextUtils {

    private static final String SEE_WORD = "вижу"; //TODO: add another language

    private TextUtils() {
    }

    public static String removeDuplicatedWords(String text) {
        // Take first sentence
        int indexOf = text.indexOf(".");
        String sentence;
        if (indexOf < 0) {
            sentence = text;
        } else {
            sentence = text.substring(0, indexOf);
        }
        // split words by comma
        String[] words = sentence.split(", ");
        // remove first 2 words (I see)
        int iSee = words[0].indexOf(SEE_WORD) + SEE_WORD.length() + 1;
        words[0] = words[0].substring(iSee);

        // get unique words
        Set<String> wordsSet = new LinkedHashSet<>(Arrays.asList(words));
        // make sentence

        String result = text.substring(0, iSee) + String.join(", ", wordsSet);

        if (indexOf < 0) {
            return result;
        } else {
            return result + text.substring(indexOf);
        }

    }
}
