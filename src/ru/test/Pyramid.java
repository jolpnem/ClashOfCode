package ru.test;

import ru.test.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Pyramid {
    public static void main(String[] args) {
        getPyramids(1).forEach(System.out::println);
        System.out.println();

        getPyramids(2).forEach(System.out::println);
        System.out.println();

        getPyramids(3, "|", "+").forEach(System.out::println);
        System.out.println();

        getPyramids(4, " ", "+").forEach(System.out::println);
    }

    public static List<String> getPyramids(int biggestPyramidLength, String symbolEmpty, String pyramidSymbol) {
        List<String> result = new ArrayList<>();

        for (int j = biggestPyramidLength - 2; j >= 0; j--) {
            for (int i = 1; i <= biggestPyramidLength - j; i++) {
                result.add(
                        StringUtil.generateLine(symbolEmpty, biggestPyramidLength - i) +
                                StringUtil.generateLine(pyramidSymbol, i * 2 - 1)
                );
            }
        }

        return result;
    }

    private static List<String> getPyramids(int biggestPyramidLength) {
        return getPyramids(biggestPyramidLength, " ", "*");
    }
}
