package com.jewtiejew.photodescriber.webservice.service.text;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TextUtilsRemoveDuplicatesTest {

    private String actual;
    private String expected;

    public TextUtilsRemoveDuplicatesTest(String actual, String expected) {
        super();
        this.actual = actual;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {
                "Я вижу человека, машину, машину, формулу 1, асфальт. Я вижу человека, Он от 35 до 52 лет, я вижу человека, Он от 26 до 43 лет,",
                "Я вижу человека, машину, формулу 1, асфальт. Я вижу человека, Он от 35 до 52 лет, я вижу человека, Он от 26 до 43 лет,"
            },{
                "Я вижу машину, машину, формулу 1, асфальт. Я вижу человека, Он от 35 до 52 лет, я вижу человека, Он от 26 до 43 лет,",
                "Я вижу машину, формулу 1, асфальт. Я вижу человека, Он от 35 до 52 лет, я вижу человека, Он от 26 до 43 лет,"
        },{
                "Я вижу ПК, ноутбук, компьютер, компьютерную клавиатуру, мебель",
                "Я вижу ПК, ноутбук, компьютер, компьютерную клавиатуру, мебель"
        },{
                "Я вижу ПК, ноутбук, компьютер, компьютерную клавиатуру, компьютер",
                "Я вижу ПК, ноутбук, компьютер, компьютерную клавиатуру"
            },{
                "Я вижу одежду, обувь, обувь, дерево, беговые ботинки.",
                "Я вижу одежду, обувь, дерево, беговые ботинки."
        },{
                "Я вижу одежду, обувь, обувь, дерево, беговые ботинки",
                "Я вижу одежду, обувь, дерево, беговые ботинки"
            }
        });
    }

    @Test
    @Parameterized.Parameters
    public void shouldRemoveDuplicatedWord() {
        assertEquals(expected, TextUtils.removeDuplicatedWords(actual));
    }
}
