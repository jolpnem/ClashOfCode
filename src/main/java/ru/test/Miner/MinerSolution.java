package ru.test.Miner;

import ru.test.util.Matrix.Matrix;

public class MinerSolution {

    public static void main(String[] args) {
        int w = 5;
        int h = 5;
        int lastMinesCount = 3;

        Matrix matrix = new Matrix(h, w);
        matrix.setRow(0, "?", "?", "1", ".", ".");
        matrix.setRow(1, "?", "?", "1", ".", ".");
        matrix.setRow(2, "1", "1", "2", "2", "2");
        matrix.setRow(3, ".", ".", "1", "?", "?");
        matrix.setRow(4, ".", ".", "1", "?", "?");

        MineSearcher searcher = new MineSearcher(matrix, lastMinesCount);
        searcher.search();

        System.out.println(matrix);
    }
}
