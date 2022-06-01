package grama.calcule.matrix;

import grama.graph.Graph;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wwwazz
 */
public class FloydWarshallTest {

    /**
     * Test of resolve method, of class FloydWarshall.
     */
    @Test
    public void resolveTest() {
        Graph graphmap = new Graph();

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");

        FloydWarshall f = FloydWarshall.initFloydWarshall(graphmap);

        String wanted = "(0, null)	(30, ville:Macon)	(50, ville:Macon)	(60, ville:Macon)	(65, ville:Meyzieu)	\n"
                + "(30, restaurant:Les Echets)	(0, null)	(80, ville:Macon)	(90, ville:Macon)	(95, ville:Meyzieu)	\n"
                + "(50, ville:Villeurbanne)	(80, ville:Macon)	(0, null)	(110, ville:Macon)	(115, ville:Meyzieu)	\n"
                + "(60, ville:Meyzieu)	(90, ville:Macon)	(110, ville:Macon)	(0, null)	(5, ville:Meyzieu)	\n"
                + "(65, ville:Meyzieu)	(95, ville:Macon)	(115, ville:Macon)	(5, restaurant:McDo-Decines)	(0, null)	\n"
                + "";

        f.resolve();

        assertEquals("1er", f.toString(), wanted);

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;A,100::R,McDo-Decines;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;N,500::V, Villeurbanne;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");

        f = FloydWarshall.initFloydWarshall(graphmap);

        wanted = "(0, null)	(30, ville:Macon)	(50, ville:Macon)	(60, ville:Macon)	(65, ville:Meyzieu)	\n"
                + "(30, restaurant:Les Echets)	(0, null)	(80, ville:Macon)	(90, ville:Macon)	(95, ville:Meyzieu)	\n"
                + "(50, ville:Villeurbanne)	(80, ville:Macon)	(0, null)	(110, ville:Macon)	(115, ville:Meyzieu)	\n"
                + "(60, ville:Meyzieu)	(90, ville:Macon)	(110, ville:Macon)	(0, null)	(5, ville:Meyzieu)	\n"
                + "(65, ville:Meyzieu)	(95, ville:Macon)	(115, ville:Macon)	(5, restaurant:McDo-Decines)	(0, null)	\n"
                + "";

        assertEquals("2 ème", f.resolve().toString(), wanted);

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");

        FloydWarshall.getInstanceKilometrage().initKilometrage(graphmap).resolve();

        wanted = "(0, null)	(30, ville:Macon)	(50, ville:Macon)	(60, ville:Macon)	(65, ville:Meyzieu)	\n"
                + "(30, restaurant:Les Echets)	(0, null)	(80, ville:Macon)	(90, ville:Macon)	(95, ville:Meyzieu)	\n"
                + "(50, ville:Villeurbanne)	(80, ville:Macon)	(0, null)	(110, ville:Macon)	(115, ville:Meyzieu)	\n"
                + "(60, ville:Meyzieu)	(90, ville:Macon)	(110, ville:Macon)	(0, null)	(5, ville:Meyzieu)	\n"
                + "(65, ville:Meyzieu)	(95, ville:Macon)	(115, ville:Macon)	(5, restaurant:McDo-Decines)	(0, null)	\n"
                + "";

        assertEquals("getInstance()", f.toString(), wanted);
    }

    /**
     * Test of initFloydWarshall method, of class FloydWarshall.
     */
    @Test
    public void initFloydWarshallTest() {
        Graph graphmap = new Graph();

        graphmap.loadFromString("V, Macon: A,30::R,Les Echets;N, 50::V, Villeurbanne;N,50::V, Villeurbanne;A,60::V,Meyzieu;;\n"
                + "R,Les Echets: A,30::V, Macon;;\n"
                + "V,Meyzieu:A,60::V,Macon;D,5::R,McDo-Decines;;\n"
                + "R,McDo-Decines:D,5::V,Meyzieu;;");

        FloydWarshall f = FloydWarshall.initFloydWarshall(graphmap);

        String wanted = "(0, null)	(30, ville:Macon)	(50, ville:Macon)	(60, ville:Macon)	(null, ville:Macon)	\n"
                + "(30, restaurant:Les Echets)	(0, null)	(null, restaurant:Les Echets)	(null, restaurant:Les Echets)	(null, restaurant:Les Echets)	\n"
                + "(50, ville:Villeurbanne)	(null, ville:Villeurbanne)	(0, null)	(null, ville:Villeurbanne)	(null, ville:Villeurbanne)	\n"
                + "(60, ville:Meyzieu)	(null, ville:Meyzieu)	(null, ville:Meyzieu)	(0, null)	(5, ville:Meyzieu)	\n"
                + "(null, restaurant:McDo-Decines)	(null, restaurant:McDo-Decines)	(null, restaurant:McDo-Decines)	(5, restaurant:McDo-Decines)	(0, null)	\n"
                + "";

        assertEquals(f.toString(), wanted);
    }

}
