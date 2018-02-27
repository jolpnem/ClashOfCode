package ru.test.util;

import java.util.function.Consumer;

public class Benchmark {
    public static <T> void test(int forks, int warMup, int count, Consumer<? super T> consumer, T param) {
        StringBuilder builder = new StringBuilder();

        long total = 0;
        for (int j = 0; j < forks; j++) {
            long totalInFork = 0;

            for (int i = 0; i < warMup; i++) {
                consumer.accept(param);
            }

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < count; i++) {
                consumer.accept(param);
            }

            totalInFork += System.currentTimeMillis() - startTime;
            builder.append(String.format("Fork: %3d, millis per action: %7f\n", j + 1, (double) totalInFork / count));
            total += totalInFork;
        }
        builder.append(String.format("Millis per action: %15f", (double) total / forks / count));

        System.err.println(builder.toString());
    }
}
