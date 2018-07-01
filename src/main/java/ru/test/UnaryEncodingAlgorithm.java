package ru.test;

import ru.test.util.StringUtil;

public class UnaryEncodingAlgorithm {
    public static void main(String[] args) {
        String MESSAGE = "%";

        String binaryRepresentation = MESSAGE.chars().mapToObj(Integer::toBinaryString)
                .reduce("", (result, binary) -> result + StringUtil.leftPadByZeros(binary, 7));
        String unaryRepresentation = binaryStringToUnary(binaryRepresentation);

        System.out.println(binaryRepresentation);
        System.out.println(unaryRepresentation);
    }

    private static String binaryStringToUnary(String binaryString) {
        StringBuilder unaryString = new StringBuilder();

        char currentChar = ' ';
        for (char ch : binaryString.toCharArray()) {
            if (currentChar != ch) {
                currentChar = ch;

                unaryString.append(" ");

                if (ch == '1') unaryString.append("0");
                else if (ch == '0') unaryString.append("00");

                unaryString.append(" ");
            }

            unaryString.append("0");
        }

        return unaryString.deleteCharAt(0).toString();
    }
}
