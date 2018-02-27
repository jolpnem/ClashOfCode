package ru.test.util.Matrix;

public class StringMatrix extends Matrix<String> {
    public StringMatrix(int rows, int cols) {
        super(rows, cols);

        data = new String[rows][cols];
    }
}
