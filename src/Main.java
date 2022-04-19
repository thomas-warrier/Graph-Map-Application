
import grama.calcule.matrix.FloydWarshall;
import grama.graph.Graph;
import grama.graph.Noeud;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Graph graphmap = new Graph();
        try {
            graphmap.loadFromFile("/home/wwwazz/Projet/Grama/res/test.csv");
            
            FloydWarshall f = FloydWarshall.initFloydWarshall(graphmap);
            f.resolve();
            System.out.println(f);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
