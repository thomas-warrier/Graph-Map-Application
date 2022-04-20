
import grama.graph.Graph;
import grama.graph.Lien;
import grama.graph.Noeud;

public class Main {

    public static void main(String[] args) {
        Graph graphmap = new Graph();

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");
        
        
        System.out.println(graphmap.getListLienOfType(Lien.Type.NATIONAL));

    }
}
