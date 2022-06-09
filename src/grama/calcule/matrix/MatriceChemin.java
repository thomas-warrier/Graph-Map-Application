package grama.calcule.matrix;

/**
 *
 * @author twarr
 */
import grama.graph.Chemin;
import grama.graph.Graph;
import grama.graph.Noeud;
import java.util.List;

public class MatriceChemin extends Matrix<Chemin> {

    private Graph graph;

    
    public MatriceChemin(Graph graph) {
        this.graph = graph;
        init(graph.getListNoeud().size(), null);

    }

    public List<List<Chemin>> getMatrix() {
        return matrix;
    }

    /**
     * requière : FloydWarshall.getInstanceKilometrage() doit être initialiser et resolved
     * @param length le taille de la matrice 4
     * @param defaut la valeur par défaut d'un élément
     */
    @Override
    public void init(int length, Chemin defaut) {
        super.init(length, null);
        FloydWarshall matrice = FloydWarshall.getInstanceKilometrage();
        for (int i = 0; i < matrix.size(); i++) {
            Noeud depart = graph.getListNoeud().get(i);
            for (int j = 0; j < matrix.size(); j++) {
                Noeud arriver = graph.getListNoeud().get(j);
                Chemin nouveau = new Chemin();

                Noeud prec = matrice.getDistByIndice(i, j).getPrec();
                Noeud curr = arriver;

                if (prec != null) {
                    while (!prec.equals(depart)) {

                        nouveau.addLienToChemin(curr.getLinkBetween(prec));

                        curr = prec;

                        prec = matrice.getDistByIndice(i, graph.getIndiceNoeud(prec)).getPrec();
                    }
                    nouveau.addLienToChemin(depart.getLinkBetween(curr));
                }

                matrix.get(i).set(j, nouveau.reversed());

            }
        }
    }

    /**
     * récuper le chemin pour aller d'un neoud à l'autre
     * @param depart le Noeud de départ
     * @param arriver le Noeud de départ
     * @return le chemin entre le noeud de départ et d'arriver
     */
    public Chemin getCheminBetween(Noeud depart, Noeud arriver) {
        int de = graph.getIndiceNoeud(depart);
        int ar = graph.getIndiceNoeud(arriver);
        return getMatrix().get(de).get(ar);
    }

    /**
     * 
     * @param depart Noeud de départ
     * @param arriver Noeud d'arriver
     * @param types la List des type de Lieux dans lesquelles on veut passer (dans l'ordre)
     * @param floydWarshall la matrice dans laquelle chercher les distances (saut ou kilométrage)
     * @return le chemin entre le neoud de départ et d'arriver passant par les Type de Noeud dans l'ordre
     */
    public Chemin getCheminBetween(Noeud depart, Noeud arriver, List<Noeud.Type> types, FloydWarshall floydWarshall) {
        Chemin chemin = new Chemin();
        Noeud prec = depart;
        
        
        for (Noeud.Type type : types) {
            Noeud plusProche = floydWarshall.getPlusProcheType(graph, prec, type);
            chemin.append(getCheminBetween(prec, plusProche));
            prec = plusProche;
            
        }
        chemin.append(getCheminBetween(prec, arriver));
        return chemin;
    }

}
