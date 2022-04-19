/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package grama.matrix;

import grama.calcule.matrix.Matrix;
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
public class MatrixTest {

    public MatrixTest() {
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
    public void testEquals() {
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
    public void testToString() {
        System.out.println("toString");
        Integer[][] arr = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        Matrix instance = new Matrix(arr);
        String expResult = "[1, 2, 3]\n[4, 5, 6]\n[7, 8, 9]\n";
        String result = instance.toString();
        assertEquals(expResult, result);

        instance = new Matrix(4, 0);
        expResult = "[0, 0, 0, 0]\n[0, 0, 0, 0]\n[0, 0, 0, 0]\n[0, 0, 0, 0]\n";
        result = instance.toString();
        assertEquals(expResult, result);
    }

}
