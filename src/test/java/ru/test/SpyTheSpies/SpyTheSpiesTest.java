package ru.test.SpyTheSpies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpyTheSpiesTest {
    @Test
    void allInCommon() {
        String[] spiesNames = "Fred Mark Kim Anita Dwayne Nick".split(" ");
        String[] suspectsDescriptions = (
                "Daniel 1 chinese\n" +
                "Clem 1 german\n" +
                "Dwayne 1 french\n" +
                "Anita 1 french\n" +
                "Spruce 1 german\n" +
                "Fred 1 french\n" +
                "Adan 1 chinese\n" +
                "Sven 1 irish\n" +
                "Nick 1 french\n" +
                "Tim 1 irish\n" +
                "Harley 1 english\n" +
                "Mary 1 russian\n" +
                "Kim 1 french\n" +
                "Rashad 1 chinese\n" +
                "Mark 1 french").split("\n");

        Detective detective = new Detective(spiesNames, suspectsDescriptions);

        String[] commands = detective.detect();

        Assertions.assertArrayEquals(new String[]{"french"}, commands);
    }

    @Test
    void commonDifferentiator() {
        String[] spiesNames = "Derick Ronaldo Tempest Rolf Jeanne Tabitha".split(" ");
        String[] suspectsDescriptions = (
                "Tabitha 1 scottish\n" +
                "Rolf 1 hebrew\n" +
                "Mohammad 1 arabic\n" +
                "Jacob 1 arabic\n" +
                "Derick 1 hebrew\n" +
                "Meta 1 arabic\n" +
                "Ronaldo 1 scottish\n" +
                "Melville 1 arabic\n" +
                "Hermon 1 arabic\n" +
                "Tempest 1 swedish\n" +
                "Jeanne 1 persian\n" +
                "Kourtney 1 arabic\n" +
                "Dallas 1 arabic\n" +
                "Vena 1 arabic\n" +
                "Eros 1 arabic").split("\n");

        Detective detective = new Detective(spiesNames, suspectsDescriptions);

        String[] commands = detective.detect();

        Assertions.assertArrayEquals(new String[]{"NOT arabic"}, commands);
    }

    @Test
    void extraAttributes() {
        String[] spiesNames = "Tabitha Mohammad Ronaldo Jeanne Vena Eros".split(" ");
        String[] suspectsDescriptions = (
                "Tabitha 2 tall thin\n" +
                "Rolf 2 blue-eyed glasses\n" +
                "Mohammad 2 thin green-eyed\n" +
                "Jacob 2 blue-eyed blond\n" +
                "Derick 2 glasses red-haired\n" +
                "Meta 2 chubby freckled\n" +
                "Ronaldo 2 tall thin\n" +
                "Melville 2 blue-eyed chubby\n" +
                "Hermon 2 tattooed blond\n" +
                "Tempest 2 chubby freckled\n" +
                "Jeanne 2 thin tall\n" +
                "Kourtney 2 blond tattooed\n" +
                "Dallas 2 glasses freckled\n" +
                "Vena 2 green-eyed thin\n" +
                "Eros 2 thin brown-haired").split("\n");

        Detective detective = new Detective(spiesNames, suspectsDescriptions);

        String[] commands = detective.detect();

        Assertions.assertArrayEquals(new String[]{"thin"}, commands);
    }

    @Test
    void extraSteps() {
        String[] spiesNames = "Albert Elenora Alton Alethea Campion Evangeline".split(" ");
        String[] suspectsDescriptions = ("Madison 3 tall french brown-eyed\n" +
                "Albert 2 tall tattooed\n" +
                "Alethea 3 tall french tattooed\n" +
                "Salazar 2 tattooed green-eyed\n" +
                "Aline 3 czech thin black-haired\n" +
                "Campion 2 tattooed tall\n" +
                "Evangeline 1 tattooed\n" +
                "Brian 3 thin tattooed green-eyed\n" +
                "Hollis 2 black-haired tall\n" +
                "Oberon 1 black-haired\n" +
                "Elenora 2 tattooed french\n" +
                "Tavian 3 french brown-eyed black-haired\n" +
                "Bracken 2 black-haired thin\n" +
                "Alton 4 tattooed green-eyed italian black-haired\n" +
                "Natalie 2 thin czech").split("\n");


        Detective detective = new Detective(spiesNames, suspectsDescriptions);

        String[] commands = detective.detect();

        Assertions.assertArrayEquals(new String[]{"italian\nNOT green-eyed\ntattooed"}, commands);
    }
}
