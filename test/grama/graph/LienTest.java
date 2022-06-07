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

        assertTrue(t.estDeType(Lien.Type.ALL));
        assertFalse(t.estDeType(Lien.Type.NONE));
        assertFalse(t.estDeType(Lien.Type.NATIONALE));

        assertTrue(Lien.Type.NONE.estDeType(Lien.Type.NONE));

        assertTrue(Lien.Type.ALL.estDeType(Lien.Type.AUTOROUTE));
    }

}
