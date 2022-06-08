package grama.graph;

import static grama.graph.Lien.Type.DEPARTEMENTALE;
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
        
        assertTrue(Lien.Type.DEPARTEMENTALE.estDeType(Lien.Type.NATIONALE.or(DEPARTEMENTALE)));
        
        assertFalse(Lien.Type.DEPARTEMENTALE.estDeType(Lien.Type.NATIONALE.or(Lien.Type.AUTOROUTE)));
    }

}
