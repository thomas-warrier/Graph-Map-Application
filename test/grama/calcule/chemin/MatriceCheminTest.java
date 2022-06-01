/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.calcule.chemin;

import grama.calcule.matrix.FloydWarshall;
import grama.calcule.matrix.MatriceChemin;
import grama.graph.Chemin;
import grama.graph.Graph;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author twarr
 */
public class MatriceCheminTest {

    @Test
    public void mainTest() {
        Graph graphmap = new Graph();
        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");
          FloydWarshall.getInstanceKilometrage().initKilometrage(graphmap).resolve();
                MatriceChemin matrice = new MatriceChemin(graphmap);
                matrice.init(graphmap.getListNoeud().size() , null);
                int i=0;
                for ( List<Chemin> arrayChemin : matrice.getMatrix()){
                    int j=0;
                    for (Chemin chemin : arrayChemin){
                                System.out.println(graphmap.getListNoeud().get(i) + " vers "  + graphmap.getListNoeud().get(j)+ " : "  + chemin.toString());
                                j++;
                    }
                    i++;
                } 
    }
}
