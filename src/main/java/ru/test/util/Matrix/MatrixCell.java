package ru.test.util.Matrix;

public final class MatrixCell {
    private final int row;
    private final int col;
    private final Object value;

    public MatrixCell(int row, int col, Object value) {
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

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(this.getClass() == obj.getClass()))
            return false;

        return this.value == ((MatrixCell) obj).value;
    }

    @Override
    public String toString() {
        return String.valueOf(value) + " [" + col + ", " + row + "]";
    }
}