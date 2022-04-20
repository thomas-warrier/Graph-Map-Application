package grama.calcule.matrix;

import grama.graph.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wwwazz
 */
public class FloydWarshallTest {

    public FloydWarshallTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of resolve method, of class FloydWarshall.
     */
    @Test
    public void testResolve() {
        Graph graphmap = new Graph();

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");

        FloydWarshall f = FloydWarshall.initFloydWarshall(graphmap);

        String wanted = "(0, null)	(30, V:Macon)	(50, V:Macon)	(60, V:Macon)	(65, V:Meyzieu)	\n"
                + "(30, R:Les Echets)	(0, null)	(80, V:Macon)	(90, V:Macon)	(95, V:Meyzieu)	\n"
                + "(50, V:Villeurbanne)	(80, V:Macon)	(0, null)	(110, V:Macon)	(115, V:Meyzieu)	\n"
                + "(60, V:Meyzieu)	(90, V:Macon)	(110, V:Macon)	(0, null)	(5, V:Meyzieu)	\n"
                + "(65, V:Meyzieu)	(95, V:Macon)	(115, V:Macon)	(5, R:McDo-Decines)	(0, null)	\n"
                + "";

        assertEquals(f.resolve().toString(), wanted);

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;A,100::R,McDo-Decines;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;N,500::V, Villeurbanne;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");

        f = FloydWarshall.initFloydWarshall(graphmap);

        wanted = "(0, null)	(30, V:Macon)	(50, V:Macon)	(60, V:Macon)	(65, V:Meyzieu)	\n"
                + "(30, R:Les Echets)	(0, null)	(80, V:Macon)	(90, V:Macon)	(95, V:Meyzieu)	\n"
                + "(50, V:Villeurbanne)	(80, V:Macon)	(0, null)	(110, V:Macon)	(115, V:Meyzieu)	\n"
                + "(60, V:Meyzieu)	(90, V:Macon)	(110, V:Macon)	(0, null)	(5, V:Meyzieu)	\n"
                + "(65, V:Meyzieu)	(95, V:Macon)	(115, V:Macon)	(5, R:McDo-Decines)	(0, null)	\n"
                + "";

        assertEquals(f.resolve().toString(), wanted);
    }

    /**
     * Test of initFloydWarshall method, of class FloydWarshall.
     */
    @Test
    public void testInitFloydWarshall() {
        Graph graphmap = new Graph();

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");

        FloydWarshall f = FloydWarshall.initFloydWarshall(graphmap);

        String wanted = "(0, null)	(30, V:Macon)	(50, V:Macon)	(60, V:Macon)	(null, V:Macon)	\n"
                + "(30, R:Les Echets)	(0, null)	(null, R:Les Echets)	(null, R:Les Echets)	(null, R:Les Echets)	\n"
                + "(50, V:Villeurbanne)	(null, V:Villeurbanne)	(0, null)	(null, V:Villeurbanne)	(null, V:Villeurbanne)	\n"
                + "(60, V:Meyzieu)	(null, V:Meyzieu)	(null, V:Meyzieu)	(0, null)	(5, V:Meyzieu)	\n"
                + "(null, R:McDo-Decines)	(null, R:McDo-Decines)	(null, R:McDo-Decines)	(5, R:McDo-Decines)	(0, null)	\n"
                + "";

        assertEquals(f.toString(), wanted);
    }

}
