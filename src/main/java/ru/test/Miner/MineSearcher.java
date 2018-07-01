package ru.test.Miner;

import ru.test.util.Matrix.Matrix;
import ru.test.util.Matrix.MatrixCell;

import java.util.List;

public class MineSearcher {
    private static final String MINE = "*";
    private static final String UNKNOWN = "?";
    private static final String EMPTY = "0";

    private Matrix matrix;
    private int remainingMinesCount;

    public MineSearcher(Matrix matrix, int remainingMinesCount) {
        this.matrix = matrix;
        this.remainingMinesCount = remainingMinesCount;
    }

    public void search() {
        while (remainingMinesCount > 0) {
            List<MatrixCell> remainingCells = matrix.getCellsByValue(UNKNOWN);
            if (remainingMinesCount == remainingCells.size()) {
                remainingCells.forEach(this::setMine);
                break;
            }

            for (int i = 1; i < 9; i++) {
                searchForUnambiguousMines(i);
                searchForUnambiguousEmptiness(i);
            }
        }

        matrix.getCellsByValue(UNKNOWN).forEach(this::setEmpty);
    }

    private void searchForUnambiguousMines(int minesAround) {
        List<MatrixCell> cells = matrix.getCellsByValue(String.valueOf(minesAround));

        cells.forEach(cell -> {
            List<MatrixCell> unknowns = matrix.getCellsAroundCellByValue(cell.getRow(), cell.getCol(), UNKNOWN);

            if (matrix.getCellsAroundCellByValue(cell.getRow(), cell.getCol(), MINE).size() + unknowns.size() == minesAround)
                unknowns.forEach(this::setMine);
        });
    }

    private void searchForUnambiguousEmptiness(int minesAround) {
        List<MatrixCell> cells = matrix.getCellsByValue(String.valueOf(minesAround));

        cells.forEach(cell -> {
            List<MatrixCell> unknowns = matrix.getCellsAroundCellByValue(cell.getRow(), cell.getCol(), UNKNOWN);

            if (matrix.getCellsAroundCellByValue(cell.getRow(), cell.getCol(), MINE).size() == minesAround)
                unknowns.forEach(this::setEmpty);
        });
    }

    private boolean checkForQuantity() {
        List<MatrixCell> map = matrix.getCellsByValue(UNKNOWN);

        if (map.size() == remainingMinesCount) {
            map.forEach(this::setMine);

            return true;
        }

        return false;
    }

    private void setMine(MatrixCell mine) {
        matrix.setCell(mine.getRow(), mine.getCol(), MINE);
        remainingMinesCount--;
    }

    private void setEmpty(MatrixCell empty) {
        int minesAround = matrix.getCellsAroundCellByValue(empty.getRow(), empty.getCol(), MINE).size();
        matrix.setCell(empty.getRow(), empty.getCol(), minesAround > 0 ? String.valueOf(minesAround) : EMPTY);
    }
}
