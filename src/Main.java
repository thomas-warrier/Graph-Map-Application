import grama.graph.Graph;
import grama.graph.Noeud;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        Graph graphmap = new Graph();
        try {
            graphmap.loadFromFile("/home/wwwazz/Projet/Grama/res/test.csv");

            for(Noeud node : graphmap.getListNoeud()){
                System.out.println(node + " = " + node.getListLien());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
