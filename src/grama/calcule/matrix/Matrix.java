package grama.calcule.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Classe représentant une Matrice Carré dont les valeur sont de type T
 * @param <T> le type générique
 */
public class Matrix<T> {

    protected T defaut;

    protected List<List<T>> matrix;

    /**
     * Initialise la matrice pour lui mettre une taille et une valeur par défaut
     * @param length la taille
     * @param defaut la valeur par défaut
     */
    public Matrix(int length, T defaut) {
        init(length, defaut);
    }
    
    public Matrix(){
        
    }

    /**
     * créé une matrice à partire d'une liste de liste
     * @param array 
     */
    public Matrix(T[][] array) {
        matrix = new ArrayList<>();
        for (T[] elemOfLine : array) {
            List<T> line = new ArrayList<>();
            line.addAll(Arrays.asList(elemOfLine));
            matrix.add(line);
        }
    }

    /**
     * copie une matrice
     * @param other la matrice à copié
     */
    public Matrix(Matrix<T> other) {
        matrix = new ArrayList<>();
        for (int i = 0; i < other.matrix.size(); i++) {
            List<T> line = new ArrayList<>();
            for (int j = 0; j < other.matrix.get(i).size(); j++) {
                line.add(other.matrix.get(i).get(j));
            }
            matrix.add(line);
        }
    }

    /**
     * Initialise la matrice pour lui mettre une taille et une valeur par défaut
     * @param length la taille
     * @param defaut la valeur par défaut
     */
    public void init(int length, T defaut) {
        this.defaut = defaut;
        matrix = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<T> line = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                line.add(defaut);
            }
            matrix.add(line);
        }
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
        String str = "";
        for (int i = 0; i < matrix.size(); i++) {

            for (int j = 0; j < matrix.get(i).size(); j++) {
                str += matrix.get(i).get(j) + "\t";
            }
            str += "\n";
        }
        return str;
    }
}
