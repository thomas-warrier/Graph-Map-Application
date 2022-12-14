package grama.calcule.matrix;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wwwazz
 */
public class MatrixTest {

    @Test
    public void equalsTest() {
        System.out.println("equals");
        Integer[][] arr = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        Matrix instance = new Matrix(arr);
        Matrix instance2 = new Matrix(arr);
        assertEquals(instance, instance2);

        arr[1][1] = 1;
        instance2 = new Matrix(arr);

        assertNotEquals(instance, instance2);

        assertNotEquals(instance2, new Matrix<Integer>(3, 0));

        assertEquals(new Matrix<Integer>(5, 7), new Matrix<Integer>(5, 7));
    }

    /**
     * Test of toString method, of class Matrix.
     */
    @Test
    public void toStringTest() {
        System.out.println("toString");
        Integer[][] arr = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        Matrix instance = new Matrix(arr);
        String expResult = "1\t2\t3\t\n4\t5\t6\t\n7\t8\t9\t\n";
        String result = instance.toString();
        assertEquals(expResult, result);

        instance = new Matrix(4, 0);
        expResult = "0\t0\t0\t0\t\n0\t0\t0\t0\t\n0\t0\t0\t0\t\n0\t0\t0\t0\t\n";
        result = instance.toString();
        assertEquals(expResult, result);
    }

}
