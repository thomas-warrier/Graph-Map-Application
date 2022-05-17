/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package grama.graph;

import grama.calcule.matrix.FloydWarshall;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wwwazz
 */
public class NoeudTest {

    @Test
    public void testTypeisType() {
        Noeud.Type t = Noeud.Type.LOISIR;

        assertTrue(t.is(Noeud.Type.ALL));
        assertFalse(t.is(Noeud.Type.NONE));
        assertFalse(t.is(Noeud.Type.VILLE));

        assertTrue(Noeud.Type.NONE.is(Noeud.Type.NONE));

        assertTrue(Noeud.Type.ALL.is(Noeud.Type.VILLE));

    }

    @Test
    public void testgetVoisin2Dist() {
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
        
        assertTrue(false);
        
    }

}
