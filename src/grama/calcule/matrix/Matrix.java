package grama.calcule.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import grama.calcule.models.SimpleOperator;
import grama.calcule.num.Num;

/**
 *
 * @author wwwazz
 * @param <T>
 */
public class Matrix<T extends SimpleOperator<T>> implements SimpleOperator<Matrix<T>>{

    private List<List<T>> matrix;

    public Matrix() {
        matrix = new ArrayList<List<T>>();
    }

    public Matrix(int length, T defaut) {
        matrix = new ArrayList<List<T>>();
        for (int i = 0; i < length; i++) {
            List<T> line = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                line.add(defaut);
            }
            matrix.add(line);
        }
    }

    public Matrix(T[][] array) {
        matrix = new ArrayList<List<T>>();
        for (int i = 0; i < array.length; i++) {
            List<T> line = new ArrayList<>();

            for (int j = 0; j < array[i].length; j++) {
                line.add(array[i][j]);
            }
            matrix.add(line);
        }
    }

    public Matrix(Matrix<T> other) {
        for (int i = 0; i < other.matrix.size(); i++) {
            matrix.add(other.matrix.get(i));
        }

    }

    @Override
    public Matrix<T> add(Matrix<T> other) {
        Matrix<T> sum = new Matrix<>(this.matrix.size(), null);
        for (int i = 0; i < matrix.size(); i++) {
            List<T> line = new ArrayList<>();

            for (int j = 0; j < matrix.get(i).size(); j++) {
                line.add(matrix.get(i).get(j).add(other.matrix.get(i).get(j)));
            }
            sum.matrix.set(i, line);
        }
        return sum;
    }

    @Override
    public Matrix<T> sub(Matrix<T> other) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Matrix<T> mul(Matrix<T> other) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public Matrix<T> mul(Num n){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Matrix<T> div(Matrix<T> other) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.matrix);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matrix<?> other = (Matrix<?>) obj;
        return this.matrix.equals(other.matrix);
    }    
    

    @Override
    public String toString() {
        return "Matrix{" + "matrix=" + matrix + '}';
    }
}
