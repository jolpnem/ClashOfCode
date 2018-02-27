package ru.test;

import ru.test.util.Exploration;

import java.util.*;

public class IteratorAndSpliterator {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>(Arrays.asList(3, 4, 5, 13, 14, 15, 23, 24, 25));
        System.out.println(Arrays.toString(ints.toArray()));

        {
            Exploration.newExploration("Iterator");

            ListIterator<Integer> iterator = ints.listIterator();

            while (iterator.hasNext()) {
                Integer i = iterator.next();

                if (i < 10) iterator.add(10 - i);
                else if (i >= 10 && i < 20) iterator.set(i - 10);
                else iterator.remove();
            }

            System.out.println("Iterated list:");
            ints.listIterator().forEachRemaining(i -> System.out.print(i + " ")); // new got iterator have new state
            System.out.println();

            System.out.print("iterator.hasNext(): ");
            System.out.println(iterator.hasNext());
            System.out.println();
        }

        {
            Exploration.newExploration("Spliterator");

            Spliterator<Integer> spliterator = ints.spliterator();

            System.out.print("spliterator.tryAdvance(System.out::print): ");
            System.out.println(spliterator.tryAdvance(System.out::print));
            System.out.print("spliterator.tryAdvance(System.out::print): ");
            System.out.println(spliterator.tryAdvance(System.out::print));

            spliterator.forEachRemaining(e -> e = e * 2);
            spliterator.trySplit();

            System.out.println(Arrays.toString(ints.toArray()));
        }
    }
}
