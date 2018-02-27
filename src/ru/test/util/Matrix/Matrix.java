package ru.test.util.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

abstract class Matrix<T> {
    protected final int rows;
    protected final int cols;
    protected T[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @SafeVarargs
    public final void setRow(int index, T... row) {
        data[index] = row;
    }

    @SafeVarargs
    public final void setCol(int index, T... col) {
        for (int i = 0; i < rows; i++) {
            data[i][index] = col[i];
        }
    }

    public void setCell(int row, int col, T cell) {
        data[row][col] = cell;
    }

    public void setCell(MatrixCell<T> cell) {
        data[cell.getRow()][cell.getCol()] = cell.getValue();
    }

    public List<MatrixCell<T>> getRow(int index) {
        List<MatrixCell<T>> cells = new ArrayList<>();

        for (int c = 0; c < cols; c++) {
            cells.add(new MatrixCell<>(index, c, data[index][c]));
        }

        return cells;
    }

    public List<MatrixCell<T>> getCol(int index) {
        List<MatrixCell<T>> cells = new ArrayList<>();

        for (int r = 0; r < cols; r++) {
            cells.add(new MatrixCell<>(r, index, data[r][index]));
        }

        return cells;
    }

    public MatrixCell<T> getCell(int row, int col) {
        return new MatrixCell<>(row, col, data[row][col]);
    }

    public Optional<MatrixCell<T>> getCell(Predicate<T> predicate) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (predicate.test(data[i][j]))
                    return Optional.of(new MatrixCell<>(i, j, data[i][j]));
            }
        }
        return Optional.empty();
    }

    public List<MatrixCell<T>> getCells(Predicate<T> predicate) {
        List<MatrixCell<T>> cells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (predicate.test(data[i][j]))
                    cells.add(new MatrixCell<>(i, j, data[i][j]));
            }
        }
        return cells;
    }

    public List<MatrixCell<T>> getCellsByValue(T value) {
        List<MatrixCell<T>> cells = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (data[r][c].equals(value)) cells.add(new MatrixCell<>(r, c, value));
            }
        }

        return cells;
    }

    public List<MatrixCell<T>> getCellsInRow(int row, T value) {
        List<MatrixCell<T>> cells = new ArrayList<>();

        for (int c = 0; c < cols; c++) {
            if (data[row][c].equals(value)) cells.add(new MatrixCell<>(row, c, value));
        }

        return cells;
    }

    public List<MatrixCell<T>> getCellsInCol(int col, T value) {
        List<MatrixCell<T>> cells = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            if (data[r][col].equals(value)) cells.add(new MatrixCell<>(r, col, value));
        }

        return cells;
    }

    public List<MatrixCell<T>> getCellsAround(int row, int col) {
        List<MatrixCell<T>> cells = new ArrayList<>();

        int top = row > 0 ? row - 1 : row;
        int bottom = row < rows - 1 ? row + 1 : row;
        int left = col > 0 ? col - 1 : col;
        int right = col < cols - 1 ? col + 1 : col;

        for (int r = top; r <= bottom; r++) {
            for (int c = left; c <= right; c++) {
                if (r == row && c == col) continue;

                cells.add(new MatrixCell<>(r, c, data[r][c]));
            }
        }

        return cells;
    }

    public List<MatrixCell<T>> getCellsAroundByValue(int row, int col, T value) {
        List<MatrixCell<T>> cells = new ArrayList<>();

        int top = row > 0 ? row - 1 : row;
        int bottom = row < rows - 1 ? row + 1 : row;
        int left = col > 0 ? col - 1 : col;
        int right = col < cols - 1 ? col + 1 : col;

        for (int r = top; r <= bottom; r++) {
            for (int c = left; c <= right; c++) {
                if (r == row && c == col) continue;

                if (data[r][c].equals(value))
                    cells.add(new MatrixCell<>(r, c, data[r][c]));
            }
        }

        return cells;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                builder.append(data[i][j]).append(" ");
            }
            builder.setCharAt(builder.length() - 1, '\n');
        }
        builder.delete(builder.length() - 1, builder.length());

        return builder.toString();
    }
}