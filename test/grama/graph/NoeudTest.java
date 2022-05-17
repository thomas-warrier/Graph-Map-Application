/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package grama.graph;

import grama.calcule.matrix.FloydWarshall;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wwwazz
 */
public class NoeudTest {

    @Test
    public void TypeisTypeTest() {
        Noeud.Type t = Noeud.Type.LOISIR;

        assertTrue(t.is(Noeud.Type.ALL));
        assertFalse(t.is(Noeud.Type.NONE));
        assertFalse(t.is(Noeud.Type.VILLE));

        assertTrue(Noeud.Type.NONE.is(Noeud.Type.NONE));

        assertTrue(Noeud.Type.ALL.is(Noeud.Type.VILLE));

    }

    @Test
    public void getVoisin2DistTest() {
        Graph graphmap = new Graph();

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;\n"
                + "L,Parck:N,15::V,Villeurbanne;A,45::V, Macon;;");

        FloydWarshall.getInstanceSaut().initSaut(graphmap).resolve();
        
             
        
        List<Noeud> voisins2 = graphmap.getListNoeud().get(0).getVoisin2Dist(graphmap, FloydWarshall.getInstanceSaut());
        assertEquals("devrait être Villeurbanne", voisins2.get(0), new Noeud(Noeud.Type.VILLE, "Villeurbanne"));
        assertEquals("devrait être McDo-Decines", voisins2.get(1), new Noeud(Noeud.Type.RESTAURANT, "McDo-Decines"));
        assertEquals("devrait être Parck", voisins2.get(2), new Noeud(Noeud.Type.LOISIR, "Parck"));        
    }

}
