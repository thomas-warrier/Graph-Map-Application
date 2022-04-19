package grama.calcule.matrix;

import grama.graph.Graph;
import grama.graph.Noeud;

class Couple {

    private int val;
    private Noeud prec;

    public int getVal() {
        return val;
    }

    public Noeud getPrec() {
        return prec;
    }

    public Couple(int val, Noeud prec) {
        this.val = val;
        this.prec = prec;
    }
}

public class FloydWarshall extends Matrix<Couple> {

    public FloydWarshall(Couple[][] array) {
        super(array);
    }

    public FloydWarshall(int length, Couple defaut) {
        super(length, defaut);
    }

    public void resolve() {
        for (int currStep = 1; currStep < matrix.size(); currStep++) {
            for (int row = 0; row < matrix.size(); row++) {
                if (row == currStep) {
                    continue;
                }
                for (int col = 0; col < matrix.size(); col++) {
                    if (row == currStep) {
                        continue;
                    }
                    int sum = matrix.get(currStep).get(col).getVal() + matrix.get(row).get(currStep).getVal();
                    Noeud previousNode = matrix.get(currStep).get(col).getPrec();
                    if (sum > matrix.get(row).get(col).getVal()) {//meilleur chemin, doit être changé
                        matrix.get(row).set(col, new Couple(sum, previousNode));
                    }
                }
            }
        }
    }

    public static FloydWarshall initFloydWarshall(Graph g) {
        FloydWarshall m = new FloydWarshall(g.getListNoeud().size(), new Couple(0, null));

        
        
        return m;
    }
}
