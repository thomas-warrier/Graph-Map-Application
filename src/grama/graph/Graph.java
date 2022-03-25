package grama.graph;

import java.util.LinkedList;
import java.util.List;

public class Graph {
    private final List<Noeud> noeuds;

    Graph() {
        noeuds = new LinkedList<>();
    }

    public void addNoeud(Noeud noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
        }
    }

    public Noeud getOrCreate(Noeud noeud) {
        for (Noeud node : noeuds) {
            if (noeud.equals(node)) {
                return node;
            }
        }
        return noeud;
    }

    public void clear() {
        noeuds.clear();
    }

    public boolean noeudExist(Noeud noeud) {
        return noeuds.contains(noeud);
    }
}
