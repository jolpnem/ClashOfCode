package ru.test.util;

public class StringUtil {
    public static String getSwapped(String s, int firstIndex, int secondIndex) {
        if (secondIndex == firstIndex) return s;

        return new StringBuilder(s.substring(0, firstIndex))
                .append(s.charAt(secondIndex))
                .append(s.substring(firstIndex + 1, secondIndex))
                .append(s.charAt(firstIndex))
                .append(s.substring(secondIndex + 1, s.length())).toString();
    }

    public static String getReversed(String s) {
        if (s.length() < 2) return s;

        StringBuilder b = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            b.append(s.substring(i, i + 1));
        }

        return b.toString();
    }


    public static String generateLine(String symbol, int count) {
        if (count == 1) return symbol;
        if (count < 1) return "";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(symbol);
        }

        return builder.toString();
    }

    public static String leftPadByZeros(String initialLine, int finalLength) {
        return String.format("%0" + finalLength + "d", Integer.parseInt(initialLine));
    }
}
