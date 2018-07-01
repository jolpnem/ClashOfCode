package ru.test.SpyTheSpies;

import java.util.*;
import java.util.stream.Stream;

class SpyTheSpies {
    public static final int SUSPECT_COUNT = 15;
    public static final int SPY_COUNT = 6;
    private static String[] spiesNames = new String[SPY_COUNT];
    private static String[] suspectsDescriptions = new String[SUSPECT_COUNT];

    public static void main(String args[]) {
        prepareData();

        Detective detective = new Detective(spiesNames, suspectsDescriptions);
        Stream<String> commands = Stream.of(detective.detect());

        commands.forEach(System.out::println);
    }

    private static void prepareData() {
        Scanner in = new Scanner(System.in);

        for(int i = 0; i < SPY_COUNT; i++)
            spiesNames[i] = in.next();

        if (in.hasNextLine())
            in.nextLine();

        for (int i = 0; i < SUSPECT_COUNT; i++)
            suspectsDescriptions[i] = in.nextLine();
    }

}
