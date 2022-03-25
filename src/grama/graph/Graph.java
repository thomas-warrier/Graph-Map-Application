package grama.graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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



    public void loadFromFile(String path) throws IOException { // y compris throw notfilefound
        File file = new File(path);
        FileInputStream stream = new FileInputStream(file);

        int unsignedByte;
        while((unsignedByte = stream.read()) > -1){
            System.out.print((char) unsignedByte);
        }
        stream.close();
    }
}
