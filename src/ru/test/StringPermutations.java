package ru.test;

public class StringPermutations {
    public static void main(String[] args) {
//        Benchmark.test(5, 15, 5, StringPermutations::mutate, "1234567");
        mutate("1234");
    }


    public static void mutate(String body) {
        mutate("", body);
    }

    public static void mutate(String prefix, String body) {
        if (body.length() == 0)
            System.out.println(prefix);

//        else Stream.of(body.split("")).forEach(e -> mutate(prefix + e,
//                StringUtil.getSwapped(body, 0, body.indexOf(e)).substring(1, body.length()))); // slowest way
        for (int i = 0; i < body.length(); i++)
            mutate(prefix + body.charAt(i), body.substring(0, i) + body.substring(i + 1, body.length())); // speediest way
//            mutate(prefix + body.charAt(i), StringUtil.getSwapped(body, 0, i).substring(1, body.length()));
    }
}