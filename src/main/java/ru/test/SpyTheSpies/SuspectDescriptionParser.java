package ru.test.SpyTheSpies;

import java.util.Arrays;

class SuspectDescriptionParser {
    static String getName(String suspectDescription) {
        String[] descParts = split(suspectDescription);
        return descParts[0];
    }

    private static String[] split(String suspectDescription) {
        return suspectDescription.split(" ");
    }

    static String[] getAttributes(String suspectDescription) {
        String[] descParts = split(suspectDescription);
        return Arrays.copyOfRange(descParts, 2, descParts.length);
    }
}
