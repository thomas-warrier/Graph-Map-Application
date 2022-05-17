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
public class LienTest {

    @Test
    public void typeisTypeTest() {
        Lien.Type t = Lien.Type.AUTOROUTE;

        assertTrue(t.is(Lien.Type.ALL));
        assertFalse(t.is(Lien.Type.NONE));
        assertFalse(t.is(Lien.Type.NATIONAL));

        assertTrue(Lien.Type.NONE.is(Lien.Type.NONE));

        assertTrue(Lien.Type.ALL.is(Lien.Type.AUTOROUTE));
    }

}
