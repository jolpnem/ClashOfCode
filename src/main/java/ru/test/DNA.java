package ru.test;

import ru.test.util.DNAComplementarity;

import java.util.Arrays;
import java.util.List;


public class DNA {
    public static void main(String[] args) {
        List<Character> dna = Arrays.asList('A', 'C', 'A', 'T', 'C', 'T', 'A', 'G', 'C', 'A', 'G');

        System.out.println(Arrays.toString(replicateDNA(dna).toArray()));
    }

    public static List<Character> replicateDNA(List<Character> dna) {
        dna.replaceAll(DNAComplementarity.map::get);

        return dna;
    }
}
