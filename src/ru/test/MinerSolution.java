package ru.test;

import ru.test.util.Matrix.MatrixCell;
import ru.test.util.Matrix.StringMatrix;

import java.util.List;

public class MinerSolution {
    public static final String MINE = "*";
    public static final String UNKNOWN = "?";
    public static final String EMPTY = ".";

    private static int lastMinesCount;

    public static void main(String[] args) {
        int w = 5;
        int h = 5;
        lastMinesCount = 3;

        StringMatrix matrix = new StringMatrix(h, w);

        matrix.setRow(0, "?", "?", "1", ".", ".");
        matrix.setRow(1, "?", "?", "1", ".", ".");
        matrix.setRow(2, "1", "1", "2", "2", "2");
        matrix.setRow(3, ".", ".", "1", "?", "?");
        matrix.setRow(4, ".", ".", "1", "?", "?");

        solution(matrix);

        System.out.println(matrix);
    }

    public static void solution(StringMatrix matrix) {
        while (lastMinesCount > 0) {
            if (checkForQuantity(matrix))
                break;

            for (int i = 1; i < 9; i++) {
                searchForUnambiguousMines(matrix, i);
                searchForUnambiguousEmptiness(matrix, i);
            }
        }

        calculateTheRemaining(matrix);
    }

    private static void searchForUnambiguousMines(StringMatrix matrix, int minesAround) {
        List<MatrixCell<String>> cells = matrix.getCellsByValue(String.valueOf(minesAround));

        cells.forEach(cell -> {
            List<MatrixCell<String>> unknowns = matrix.getCellsAroundByValue(cell.getRow(), cell.getCol(), UNKNOWN);

            if (matrix.getCellsAroundByValue(cell.getRow(), cell.getCol(), MINE).size() + unknowns.size() == minesAround)
                unknowns.forEach((mine) -> MinerSolution.mine(matrix, mine));
        });
    }

    private static void searchForUnambiguousEmptiness(StringMatrix matrix, int minesAround) {
        List<MatrixCell<String>> cells = matrix.getCellsByValue(String.valueOf(minesAround));

        cells.forEach(cell -> {
            List<MatrixCell<String>> unknowns = matrix.getCellsAroundByValue(cell.getRow(), cell.getCol(), UNKNOWN);

            if (matrix.getCellsAroundByValue(cell.getRow(), cell.getCol(), MINE).size() == minesAround)
                unknowns.forEach((unknown) -> MinerSolution.empty(matrix, unknown));
        });
    }

    private static boolean checkForQuantity(StringMatrix matrix) {
        List<MatrixCell<String>> map = matrix.getCellsByValue(UNKNOWN);

        if (map.size() == lastMinesCount) {
            map.forEach((c) -> MinerSolution.mine(matrix, c));

            return true;
        }

        return false;
    }

    private static void calculateTheRemaining(StringMatrix matrix) {
        matrix.getCellsByValue(UNKNOWN).forEach(unknown -> empty(matrix, unknown));
    }

    private static void mine(StringMatrix matrix, MatrixCell<String> mine) {
        matrix.setCell(mine.getRow(), mine.getCol(), MINE);
        lastMinesCount--;
    }

    private static void empty(StringMatrix matrix, MatrixCell<String> empty) {
        int minesAround = matrix.getCellsAroundByValue(empty.getRow(), empty.getCol(), MINE).size();
        matrix.setCell(empty.getRow(), empty.getCol(), minesAround > 0 ? String.valueOf(minesAround) : EMPTY);
    }
}
