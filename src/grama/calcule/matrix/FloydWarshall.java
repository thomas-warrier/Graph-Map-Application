package grama.calcule.matrix;

import grama.graph.Graph;
import grama.graph.Noeud;

public class FloydWarshall extends Matrix<FloydWarshall.Couple> {

    static class Couple {

        private Integer val;//si val est null alors on considérera que la valeur est infinie.
        private Noeud prec;

        public Couple(Integer val, Noeud prec) {
            this.val = val;
            this.prec = prec;
        }

        @Override
        public String toString() {
            return "(" + val + ", " + prec + ')';
        }

    }

    public FloydWarshall(Matrix<Couple> other) {
        super(other);
    }
    
    public FloydWarshall(Couple[][] array) {
        super(array);
    }

    public FloydWarshall(int length, Couple defaut) {
        super(length, defaut);
    }

    public FloydWarshall resolve() {
        FloydWarshall resolved = new FloydWarshall(this);
        for (int currStep = 0; currStep < resolved.matrix.size(); currStep++) {
            for (int row = 0; row < resolved.matrix.size(); row++) {
                if (row == currStep) {
                    continue;
                }
                for (int col = 0; col < resolved.matrix.size(); col++) {
                    if (row == currStep) {
                        continue;
                    }

                    if (row != col /*optimisation*/ && resolved.matrix.get(currStep).get(col).val != null && resolved.matrix.get(row).get(currStep).val != null) {//si additionne avec un infini (null) => forcément pas mieux
                        int sum = resolved.matrix.get(currStep).get(col).val + resolved.matrix.get(row).get(currStep).val;
                        Noeud previousNode = resolved.matrix.get(currStep).get(col).prec;
                        if (resolved.matrix.get(row).get(col).val == null || sum < resolved.matrix.get(row).get(col).val) {//meilleur chemin, doit être changé
                            resolved.matrix.get(row).set(col, new Couple(sum, previousNode));
                        }
                    }

                }
            }
        }
        
        return resolved;
    }

    public static FloydWarshall initFloydWarshall(Graph g) {
        FloydWarshall m = new FloydWarshall(g.getListNoeud().size(), new Couple(null, null));

        for (int row = 0; row < m.matrix.size(); row++) {
            for (int col = 0; col < m.matrix.size(); col++) {
                if (col == row) {
                    m.matrix.get(row).set(col, new Couple(0, null));
                } else {
                    Integer distance = g.getListNoeud().get(row).getDistanceTo(g.getListNoeud().get(col));
                    m.matrix.get(row).set(col, new Couple(distance, g.getListNoeud().get(row)));
                }
            }
        }

        return m;
    }
}
