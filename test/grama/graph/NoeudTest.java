/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package grama.graph;

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

}
