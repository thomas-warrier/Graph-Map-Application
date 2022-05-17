
import grama.calcule.matrix.FloydWarshall;
import grama.graph.Graph;
import grama.graph.Noeud;
import grama.ihm.MainInterface;

public class Main {

    public static void main(String[] args) {
        //MainInterface.start();
        Graph graphmap = new Graph();

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;\n"
                + "L,Parck:N,15::V,Villeurbanne;A,45::V, Macon;;");

        FloydWarshall.getInstanceSaut().initSaut(graphmap).resolve();
        
             
        
        System.out.println("noeud de départ  : " + graphmap.getListNoeud().get(0));
        for( Noeud noeud : graphmap.getListNoeud().get(0).getVoisin2Dist(graphmap, FloydWarshall.getInstanceSaut())){
            System.out.println(noeud);
        }

    }
}
