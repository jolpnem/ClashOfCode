package ru.test;

public class NumberLinesFrom1ToN {
    public static void main(String[] args) {
        int lines = 10;


        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= lines; i++) {
            StringBuilder line = new StringBuilder();

            for (int k = i; k >= 1; k--)
                if (i % 2 == 0)
                    line.append(k).append("-");
                else
                    line.insert(0, k).insert(0, "-");

            if (i % 2 == 0)
                line.delete(line.length()-1, line.length());
            else
                line.delete(0, 1);

            result.append(line).append("\n");
        }

        System.out.println(result.toString());
    }
}
