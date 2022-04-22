/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package grama.graph;

import grama.calcule.vector.Vector2D;
import java.awt.Font;
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
public class LienTest {
    
    public LienTest() {
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

    @Test
    public void testTypeisType() {
        Lien.Type t = Lien.Type.AUTOROUTE;

        assertTrue(t.is(Lien.Type.ALL));
        assertFalse(t.is(Lien.Type.NONE));
        assertFalse(t.is(Lien.Type.NATIONAL));

        assertTrue(Lien.Type.NONE.is(Lien.Type.NONE));

        assertTrue(Lien.Type.ALL.is(Lien.Type.AUTOROUTE));
    }
    
}
