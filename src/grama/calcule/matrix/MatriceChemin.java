/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
     * @prec FloydWarshall.getInstanceKilometrage() doit être initialiser & resolved
     * @param length
     * @param defaut
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
     * @return 
     */
    public Chemin getCheminBetween(Noeud depart, Noeud arriver) {
        int de = graph.getIndiceNoeud(depart);
        int ar = graph.getIndiceNoeud(arriver);
        return getMatrix().get(de).get(ar);
    }

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
