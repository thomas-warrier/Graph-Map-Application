package grama.calcule.vector;

import java.awt.Graphics;
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
public class Vector2DTest {

    public Vector2DTest() {
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
     * Test of add method, of class Vector2D.
     */
    @Test
    public void testAdd() {
        Vector2D other = new Vector2D(3, 4);
        Vector2D instance = new Vector2D(2, 3);
        Vector2D expResult = new Vector2D(5, 7);
        Vector2D result = instance.add(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of sub method, of class Vector2D.
     */
    @Test
    public void testSub() {
        Vector2D instance = new Vector2D(2, 3);
        Vector2D other = new Vector2D(3, 4);
        Vector2D expResult = new Vector2D(-1, -1);
        Vector2D result = instance.sub(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of mul method, of class Vector2D.
     */
    @Test
    public void testMul() {
        double a = 2.0;
        Vector2D instance = new Vector2D(2, 3);
        Vector2D expResult = new Vector2D(4, 6);
        Vector2D result = instance.mul(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of div method, of class Vector2D.
     */
    @Test
    public void testDiv() {
        double a = 10.0;
        Vector2D instance = new Vector2D(10, 50);
        Vector2D expResult = new Vector2D(1, 5);
        Vector2D result = instance.div(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of norm method, of class Vector2D.
     */
    @Test
    public void testNorm() {
        Double expResult = Math.sqrt(45);
        Vector2D instance = new Vector2D(3, 6);
        Double result = instance.norm();
        assertEquals(expResult, result);
    }

    /**
     * Test of unitaire method, of class Vector2D.
     */
    @Test
    public void testUnitaire() {
        Vector2D instance = new Vector2D(0, 10);
        Vector2D expResult = new Vector2D(0, 1);
        Vector2D result = instance.unitaire();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVecWithOrientation method, of class Vector2D.
     */
    @Test
    public void testGetVecWithOrientation() {
        Vector2D instance = new Vector2D(-1, 0);
        Vector2D expResult = new Vector2D(1, 0);
        Vector2D result = instance.getVecWithOrientation(0.0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrientation method, of class Vector2D.
     */
    @Test
    public void testGetOrientation() {
        Vector2D instance = new Vector2D(1, 0);
        Double expResult = 0.0;
        Double result = instance.getOrientation();
        assertEquals(expResult, result);
    }

    /**
     * Test of rotateOf method, of class Vector2D.
     */
    @Test
    public void testRotateOf() {
        double r = Math.PI;
        Vector2D instance = new Vector2D(1, 0);
        Vector2D result = instance.rotateOf(r);
        assertTrue("x error", result.x >= -1.0001 && result.x <= -0.99999);
        assertTrue("y error", result.y >= -0.0001 && result.y <= 0.00001);
    }

    /**
     * Test of equals method, of class Vector2D.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Vector2D(1, 3);
        Vector2D instance = new Vector2D(1, -3);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

}
