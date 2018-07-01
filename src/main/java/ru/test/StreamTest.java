package ru.test;

import ru.test.util.Exploration;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class StreamTest {
    public static void main(String[] args) {
        mapAndFlatMap();

        distinct();

        limitAndSkip();

        takeWhileAndDropWhile();

        collect();

        reduce();

        anyMatchAndAllMatchAndNoneMatch();

        builder();
    }

    private static void builder() {
        {
            Exploration.newExploration("Stream.builder()");

            java.util.stream.Stream<Integer> ints = java.util.stream.Stream.builder().add(5).add(4).add(7).build().map(e -> (Integer) e);

            System.out.println("Built stream");
            System.out.println("array from stream:");
            System.out.println(Arrays.toString(ints.toArray()));
        }
    }

    private static void anyMatchAndAllMatchAndNoneMatch() {
        {
            Exploration.newExploration("Stream.anyMatch() and Stream.allMatch() and Stream.noneMatch()");

            Integer[] ints = {0, 1, 2, 7, 8, 9, 50, 51, 52, 53, 999, 9999, 99999};
            System.out.println(Arrays.toString(ints));

            anyMatch(ints);

            System.out.println();

            allMatch(ints);

            System.out.println();

            noneMatch(ints);

            System.out.println();
        }
    }

    private static void noneMatch(Integer[] ints) {
        boolean noneMatch;
        noneMatch = java.util.stream.Stream.of(ints).noneMatch(e -> e == 9);
        System.out.println("noneMatch(ints) by e == 9 is " + noneMatch);

        noneMatch = java.util.stream.Stream.of(ints).noneMatch(e -> e == 10);
        System.out.println("noneMatch(ints) by e == 10 is " + noneMatch);

        noneMatch = java.util.stream.Stream.of(ints).noneMatch(e -> e < 999999999);
        System.out.println("noneMatch(ints) by e < 999999999 is " + noneMatch);
    }

    private static void allMatch(Integer[] ints) {
        boolean allMatch;
        allMatch = java.util.stream.Stream.of(ints).allMatch(e -> e == 9);
        System.out.println("allMatch(ints) by e == 9 is " + allMatch);

        allMatch = java.util.stream.Stream.of(ints).allMatch(e -> e == 10);
        System.out.println("allMatch(ints) by e == 10 is " + allMatch);

        allMatch = java.util.stream.Stream.of(ints).allMatch(e -> e < 999999999);
        System.out.println("allMatch(ints) by e < 999999999 is " + allMatch);
    }

    private static void anyMatch(Integer[] ints) {
        boolean anyMatch;

        anyMatch = java.util.stream.Stream.of(ints).anyMatch(e -> e == 9);
        System.out.println("anyMatch(ints) by e == 9 is " + anyMatch);

        anyMatch = java.util.stream.Stream.of(ints).anyMatch(e -> e == 10);
        System.out.println("anyMatch(ints) by e == 10 is " + anyMatch);

        anyMatch = java.util.stream.Stream.of(ints).anyMatch(e -> e < 999999999);
        System.out.println("anyMatch(ints) by e < 999999999 is " + anyMatch);
    }

    private static void reduce() {
        {
            Exploration.newExploration("Stream.reduce()");

            Integer[] ints = {2, 3, 4, 1};
            System.out.println(Arrays.toString(ints));

            Integer reduced = java.util.stream.Stream.of(ints).reduce(0, (a, b) -> a + b);
            System.out.println("Reduced ints to int:");
            System.out.println(reduced);

            System.out.println();
        }
    }

    private static void collect() {
        {
            Exploration.newExploration("Stream.collect()");

            Integer[] ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            System.out.println(Arrays.toString(ints));

            Deque<Integer> collected = java.util.stream.Stream.of(ints).collect(ArrayDeque::new, ArrayDeque::add, ArrayDeque::addAll);
            System.out.println("Collected ints to ArrayDeque");
            System.out.println(collected);

            System.out.println();
        }
    }

    private static void takeWhileAndDropWhile() {
        {
            Exploration.newExploration("Stream.takeWhile() and Stream.dropWhile()");

            @SuppressWarnings("SpellCheckingInspection") String[] lines = {"abc", "jkl", "stu", "repeat", "new repeating", "some else?", "abcd", "abc", "jka", "abcde", "abc"};
            System.out.println(Arrays.toString(lines));

            java.util.stream.Stream<String> takeWhile = java.util.stream.Stream.of(lines).takeWhile(line -> line.length() < 5);
            System.out.println("take line while line.length() < 5");
            System.out.println(Arrays.toString(takeWhile.toArray()));

            java.util.stream.Stream<String> dropWhile = java.util.stream.Stream.of(lines).dropWhile(line -> line.length() < 5);
            System.out.println("drop line while line.length() < 5");
            System.out.println(Arrays.toString(dropWhile.toArray()));

            System.out.println();
        }
    }

    private static void limitAndSkip() {
        {
            Exploration.newExploration("Stream.limit() and Stream.skip()");

            String[] lines = {"abc def ghi", "jkl mno pqr", "stu vwx yz", "repeat", "new repeating", "some else?"};
            System.out.println(Arrays.toString(lines));

            java.util.stream.Stream<String> limited = java.util.stream.Stream.of(lines).limit(4);
            System.out.println("Limited by 4 elements:");
            System.out.println(Arrays.toString(limited.toArray()));

            java.util.stream.Stream<String> skipped = java.util.stream.Stream.of(lines).skip(4);
            System.out.println("Skipped 4 elements:");
            System.out.println(Arrays.toString(skipped.toArray()));

            System.out.println();
        }
    }

    private static void distinct() {
        {
            Exploration.newExploration("Stream.distinct()");

            String[] voices = {"vasya", "petya", "katya", "stepa", "vasya", "stepa", "yura"};
            System.out.println(Arrays.toString(voices));

            java.util.stream.Stream<String> orderedVoices = java.util.stream.Stream.of(voices).distinct();
            System.out.println(Arrays.toString(orderedVoices.toArray()));

            System.out.println();
        }
    }

    private static void mapAndFlatMap() {
        {
            Exploration.newExploration("Stream.map() and Stream.flatMap()");

            String[] file = {"abc def ghi", "jkl mno pqr", "stu vwx yz"};
            System.out.println(Arrays.toString(file));

            java.util.stream.Stream<String> lines = java.util.stream.Stream.of(file).map(line -> line + "!"); // map требует Function типизированный типом line
            System.out.println(Arrays.toString(lines.toArray()));

            java.util.stream.Stream<String> words = java.util.stream.Stream.of(file).flatMap(line -> java.util.stream.Stream.of(line.split(" +"))); // flatMap требует Function типизированный Stream'ом, который типизирован типом line
            System.out.println(Arrays.toString(words.toArray()));

            System.out.println();
        }
    }
}
