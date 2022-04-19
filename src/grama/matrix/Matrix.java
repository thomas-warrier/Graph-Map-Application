package grama.matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix<T> implements Comparable<Matrix<T>> {

    private List<List<T>> matrix;

    public Matrix() {
        matrix = new ArrayList<List<T>>();
    }

    public Matrix(T[][] array) {
        for (int i = 0; i < array.length; i++) {
            matrix.add(new ArrayList<T>());
            for (int j = 0; j < array[i].length; j++) {
                matrix.get(i).add(array[i][j]);
            }
        }
    }

    public Matrix(Matrix<T> other) {
        for (int i = 0; i < other.matrix.size(); i++) {
            matrix.add(other.matrix.get(i));
        }

    }

    @Override
    public int compareTo(Matrix<T> o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "Matrix{" + "matrix=" + matrix + '}';
    }
}
