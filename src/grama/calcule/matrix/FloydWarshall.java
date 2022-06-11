package grama.calcule.matrix;

import grama.graph.Graph;
import grama.graph.Noeud;
import java.util.List;

/**
 * Une matrice qui permet de résoudres les plus cours chemins d'un graph
 *
 * @author virgile
 */
public class FloydWarshall extends Matrix<FloydWarshall.Couple> {

    /**
     *
     * @return L'instance static De la matrice FloydWarshall
     */
    private static FloydWarshall instanceKilometrage;
    private static FloydWarshall instanceSaut;

    /**
     *
     * @return une instance static de FloydWarshall déstiner à trouvé les plus crous chemin en terme de kilométrage
     */
    public static FloydWarshall getInstanceKilometrage() {
        if (instanceKilometrage == null) {
            instanceKilometrage = new FloydWarshall(0, new Couple(null, null));
        }
        return instanceKilometrage;
    }

    /**
     *
     * @return une instance static de FloydWarshall déstiner à trouvé les plus crous chemin en terme de saut (nombre de lien traverser)
     */
    public static FloydWarshall getInstanceSaut() {
        if (instanceSaut == null) {
            instanceSaut = new FloydWarshall(0, new Couple(null, null));
        }
        return instanceSaut;
    }

    /**
     * permet de stocker un valeur et le Noeud Précédant
     */
    public static class Couple {

        /**
         *
         * @return la valeur, si null alors on considérera que la valeur est infinie
         */
        public Integer getVal() {
            return val;
        }

        /**
         *
         * @return Le Noeud Précédant
         */
        public Noeud getPrec() {
            return prec;
        }

        private Integer val;//si val est null alors on considérera que la valeur est infinie
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

    public FloydWarshall(Graph g) {
        super(g.getListNoeud().size(), new Couple(null, null));

        initKilometrage(g);
    }

    /**
     * Résouse avec l'algorithme de FloyWarshall
     *
     * @return this (pour facilité la manipulation en une ligne)
     */
    public FloydWarshall resolve() {
        for (int currStep = 0; currStep < matrix.size(); currStep++) {
            for (int row = 0; row < matrix.size(); row++) {
                if (row == currStep) {
                    continue;
                }
                for (int col = 0; col < matrix.size(); col++) {
                    if (row == currStep) {
                        continue;
                    }

                    if (row != col /*optimisation*/ && matrix.get(currStep).get(col).val != null && matrix.get(row).get(currStep).val != null) {//si additionne avec un infini (null) => forcément pas mieux
                        int sum = matrix.get(currStep).get(col).val + matrix.get(row).get(currStep).val;
                        Noeud previousNode = matrix.get(currStep).get(col).prec;
                        if (matrix.get(row).get(col).val == null || sum < matrix.get(row).get(col).val) {//meilleur chemin, doit être changé
                            matrix.get(row).set(col, new Couple(sum, previousNode));
                        }
                    }

                }
            }
        }

        return this;
    }

    /**
     * Initialize comme re créé un objet FloyWarshall pour le kilométrage
     *
     * @param g le graph avec lequelle initialiser
     * @return this (pour facilité la manipulation en une ligne)
     */
    public FloydWarshall initKilometrage(Graph g) {
        super.init(g.getListNoeud().size(), new Couple(null, null));

        for (int row = 0; row < matrix.size(); row++) {
            for (int col = 0; col < matrix.size(); col++) {
                if (col == row) {
                    matrix.get(row).set(col, new Couple(0, null));
                } else {
                    Integer distance = g.getListNoeud().get(row).getDistanceTo(g.getListNoeud().get(col));
                    matrix.get(row).set(col, new Couple(distance, g.getListNoeud().get(row)));
                }
            }
        }
        return this;
    }

    /**
     * Initialize comme re créé un objet FloyWarshall pour le nombre de saut
     *
     * @param g le graph avec lequelle initialiser
     * @return this (pour facilité la manipulation en une ligne)
     */
    public FloydWarshall initSaut(Graph g) {
        super.init(g.getListNoeud().size(), new Couple(null, null));

        for (int row = 0; row < matrix.size(); row++) {
            for (int col = 0; col < matrix.size(); col++) {
                if (col == row) {
                    matrix.get(row).set(col, new Couple(0, null));
                } else {
                    Integer nbSaut = null;
                    if (g.getListNoeud().get(row).getVoisinsOfType(Noeud.Type.ALL).contains(g.getListNoeud().get(col))) {
                        nbSaut = 1;
                    }

                    matrix.get(row).set(col, new Couple(nbSaut, g.getListNoeud().get(row)));
                }
            }
        }
        return this;
    }

    /**
     *
     * @param indiceNoeudDep l'indice du neoud de départ
     * @param indiceNoeudArr l'indice du neoud de d'arriver
     * @return la distance entre les noeuds
     */
    public Couple getDistByIndice(int indiceNoeudDep, int indiceNoeudArr) {
        return matrix.get(indiceNoeudDep).get(indiceNoeudArr);
    }

    /**
     * Permet de récuperer le Noeud le plus proche d'un certain neoud
     *
     * @param g le graphe dans le quelle son les neouds
     * @param depart le neoud duquelle partire pour trouver le noeud le plus proche
     * @param listeWhereLook la liste des noeuds où on veux chercher le plus proche
     * @param withoutNoeuds la liste des noeuds où on ne veux pas chercher le noeud le plus proche
     * @return Le noeud le plus proche du noeud de départ si il existe
     */
    public Noeud getPlusProcheIn(Graph g, Noeud depart, List<Noeud> listeWhereLook, List<Noeud> withoutNoeuds) {
        Couple plusProche = null;
        Noeud arriver = null;
        int indiceDepart = g.getIndiceNoeud(depart);
        for (Noeud node : listeWhereLook) {
            if (!withoutNoeuds.contains(node)) {
                Couple newCouple = getDistByIndice(indiceDepart, g.getIndiceNoeud(node));
                if (plusProche == null || plusProche.val > newCouple.val) {
                    plusProche = newCouple;
                    arriver = node;
                }
            }
        }
        return arriver;
    }
}
