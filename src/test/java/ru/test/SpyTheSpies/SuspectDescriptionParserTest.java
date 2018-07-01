package ru.test.SpyTheSpies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SuspectDescriptionParserTest {
    @Test
    void getName() {
        Assertions.assertEquals("Nadya", SuspectDescriptionParser.getName("Nadya 2 beautiful lazy"));
    }

    @Test
    void getAttributes() {
        Assertions.assertArrayEquals(new String[]{"beautiful", "lazy"}, SuspectDescriptionParser.getAttributes("Nadya 2 beautiful lazy"));
    }
}
