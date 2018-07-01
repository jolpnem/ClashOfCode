package ru.test.util.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private final Object[][] matrixStructure;
    private final int colCount;
    private final int rowCount;

    public Matrix(int rowCount, int colCount) {
        this.matrixStructure = new Object[rowCount][colCount];
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    public final void setRow(int rowIndex, Object... rowElements) {
        matrixStructure[rowIndex] = rowElements;
    }

    public final void setCol(int colIndex, Object... colElements) {
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            matrixStructure[rowIndex][colIndex] = colElements[rowIndex];
        }
    }

    public void setCell(int rowIndex, int colIndex, Object value) {
        matrixStructure[rowIndex][colIndex] = value;
    }

    public void setCell(MatrixCell cell) {
        setCell(cell.getRow(), cell.getCol(), cell.getValue());
    }

    public List<MatrixCell> getCellsByValue(Object value) {
        List<MatrixCell> cells = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++)
            for (int colIndex = 0; colIndex < colCount; colIndex++)
                if (matrixStructure[rowIndex][colIndex].equals(value)) cells.add(new MatrixCell(rowIndex, colIndex, value));

        return cells;
    }

    public List<MatrixCell> getCellsAroundCellByValue(int cellRowIndex, int cellColIndex, Object value) {
        List<MatrixCell> cells = new ArrayList<>();
        SubMatrixAroundCell subMatrix = new SubMatrixAroundCell(cellRowIndex, cellColIndex).calculateBounds();

        for (int rowIndex = subMatrix.getTop(); rowIndex <= subMatrix.getBottom(); rowIndex++) {
            for (int colIndex = subMatrix.getLeft(); colIndex <= subMatrix.getRight(); colIndex++) {
                if (rowIndex == cellRowIndex && colIndex == cellColIndex) continue;

                if (matrixStructure[rowIndex][colIndex].equals(value))
                    cells.add(new MatrixCell(rowIndex, colIndex, matrixStructure[rowIndex][colIndex]));
            }
        }

        return cells;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                builder.append(matrixStructure[i][j]).append(" ");
            }
            builder.setCharAt(builder.length() - 1, '\n');
        }
        builder.delete(builder.length() - 1, builder.length());

        return builder.toString();
    }

    private class SubMatrixAroundCell {
        private final int cellRowIndex;
        private final int cellColIndex;
        private int top;
        private int bottom;
        private int left;
        private int right;

        public SubMatrixAroundCell(int cellRowIndex, int cellColIndex) {
            this.cellRowIndex = cellRowIndex;
            this.cellColIndex = cellColIndex;
        }

        public int getTop() {
            return top;
        }

        public int getBottom() {
            return bottom;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public SubMatrixAroundCell calculateBounds() {
            top = cellRowIndex > 0 ? cellRowIndex - 1 : cellRowIndex;
            bottom = cellRowIndex < rowCount - 1 ? cellRowIndex + 1 : cellRowIndex;
            left = cellColIndex > 0 ? cellColIndex - 1 : cellColIndex;
            right = cellColIndex < colCount - 1 ? cellColIndex + 1 : cellColIndex;

            return this;
        }
    }
}