package ru.test.util.Matrix;

public final class MatrixCell<T> {
    private final int row;
    private final int col;
    private final T value;

    public MatrixCell(int row, int col, T value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return value.getClass().equals(obj.getClass());
    }

    @Override
    public String toString() {
        return new StringBuilder().append(value)
                .append(" [").append(col).append(", ").append(row).append("]").toString();
    }
}