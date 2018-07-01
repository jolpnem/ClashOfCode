package ru.test.SpyTheSpies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpyTheSpiesTest {
    @Test
    void allInCommon() {
        String[] spiesNames = "Fred Mark Kim Anita Dwayne Nick".split(" ");
        String[] suspectsDescriptions = "Daniel 1 chinese\nClem 1 german\nDwayne 1 french\nAnita 1 french\nSpruce 1 german\nFred 1 french\nAdan 1 chinese\nSven 1 irish\nNick 1 french\nTim 1 irish\nHarley 1 english\nMary 1 russian\nKim 1 french\nRashad 1 chinese\nMark 1 french".split("\n");

        Detective detective = new Detective(spiesNames, suspectsDescriptions);

        String[] commands = detective.detect();

        Assertions.assertArrayEquals(new String[]{"french"}, commands);
    }
}
