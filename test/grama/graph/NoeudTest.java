package grama.graph;

import grama.calcule.matrix.FloydWarshall;
import grama.calcule.vector.Vector2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author wwwazz
 */
public class NoeudTest {

    Noeud lyon, burgerking, asterix;
     Graph graphmap;

    public NoeudTest() {
        lyon = new Noeud(Noeud.Type.VILLE, "Lyon");
        burgerking = new Noeud(Noeud.Type.RESTAURANT, "Burgerking");
        asterix = new Noeud(Noeud.Type.LOISIR, "Asterix");
        
        graphmap = new Graph();

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;\n"
                + "L,Parck:N,15::V,Villeurbanne;A,45::V, Macon;;");

        FloydWarshall.getInstanceSaut().initSaut(graphmap).resolve();
    }

    @Test
    public void typeisTypeTest() {
        Noeud.Type t = Noeud.Type.LOISIR;

        assertTrue(t.estDeType(Noeud.Type.ALL));
        assertFalse(t.estDeType(Noeud.Type.NONE));
        assertFalse(t.estDeType(Noeud.Type.VILLE));

        assertTrue(t.estDeType(Noeud.Type.LOISIR.or(Noeud.Type.VILLE)));
        assertFalse(t.estDeType(Noeud.Type.RESTAURANT.or(Noeud.Type.VILLE)));

        assertTrue(Noeud.Type.NONE.estDeType(Noeud.Type.NONE));

        assertTrue(Noeud.Type.ALL.estDeType(Noeud.Type.VILLE));

        assertTrue(Noeud.Type.VILLE.estDeType(Noeud.Type.VILLE));

        assertFalse(Noeud.Type.LOISIR.estDeType(Noeud.Type.VILLE));

    }

    @Test
    public void getVoisin2DistTest() {

        List<Noeud> voisins2 = graphmap.getListNoeud().get(0).getVoisin2Dist(graphmap, FloydWarshall.getInstanceSaut(), Noeud.Type.ALL);

        assertEquals("devrait être Villeurbanne", voisins2.get(0), new Noeud(Noeud.Type.VILLE, "Villeurbanne"));
        assertEquals("devrait être McDo-Decines", voisins2.get(1), new Noeud(Noeud.Type.RESTAURANT, "McDo-Decines"));
        assertEquals("devrait être Parck", voisins2.get(2), new Noeud(Noeud.Type.LOISIR, "Parck"));
    }

    /**
     * Test of getTypeLieu method, of class Noeud.
     */
    @Test
    public void testGetTypeLieu() {
        assertEquals(Noeud.Type.VILLE, lyon.getTypeLieu());
        assertEquals(Noeud.Type.RESTAURANT, burgerking.getTypeLieu());
        assertEquals(Noeud.Type.LOISIR, asterix.getTypeLieu());

        assertNotEquals(Noeud.Type.LOISIR, lyon.getTypeLieu());
        assertNotEquals(Noeud.Type.VILLE, burgerking.getTypeLieu());
        assertNotEquals(Noeud.Type.RESTAURANT, asterix.getTypeLieu());
    }

    /**
     * Test of equals method, of class Noeud.
     */
    @Test
    public void testEquals() {
        assertEquals(new Noeud(Noeud.Type.VILLE, "Lyon"), lyon);

        assertNotEquals(lyon, burgerking);
        assertNotEquals(lyon, asterix);
        assertNotEquals(asterix, burgerking);
    }

    /**
     * Test of toString method, of class Noeud.
     */
    @Test
    public void testToString() {
        assertEquals("ville:Lyon", lyon.toString());
        assertEquals("restaurant:Burgerking", burgerking.toString());
        assertEquals("loisir:Asterix", asterix.toString());
    }

    /**
     * Test of compareOpeningTo method, of class Noeud.
     */
    @Test
    public void testCompareOpeningTo() {
        int comparaison = Noeud.compareOpeningTo(graphmap.getListNoeud().get(0), graphmap.getListNoeud().get(1), graphmap, FloydWarshall.getInstanceSaut(), Noeud.Type.RESTAURANT);
        assertTrue(comparaison == 0);
    }
}
