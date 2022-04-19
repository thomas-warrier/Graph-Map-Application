package grama.calcule.matrix;

import grama.graph.Graph;
import grama.graph.Noeud;
import java.io.IOException;
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
        System.out.println("resolve");
        FloydWarshall instance = null;
        instance.resolve();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initFloydWarshall method, of class FloydWarshall.
     */
    @Test
    public void testInitFloydWarshall() {
        Graph graphmap = new Graph();
        try {
            graphmap.loadFromFile("/home/wwwazz/Projet/Grama/res/test.csv");

            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
