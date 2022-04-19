/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package grama.matrix;

import grama.calcule.matrix.Matrix;
import grama.calcule.num.Num;
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
        Num[][] arr = {
            {new Num(1), new Num(2), new Num(3)},
            {new Num(4), new Num(5), new Num(6)},
            {new Num(7), new Num(8), new Num(9)}
        };
        Matrix instance = new Matrix(arr);
        Matrix instance2 = new Matrix(arr);
        assertEquals(instance, instance2);

        arr[1][1] = new Num(1);
        instance2 = new Matrix(arr);

        assertNotEquals(instance, instance2);
        
        assertNotEquals(instance2, new Matrix<Num>(3, new Num(0)));
        
        assertEquals(new Matrix<Num>(5, new Num(7)), new Matrix<Num>(5, new Num(7)));
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        Num[][] arr = {
            {new Num(1), new Num(2), new Num(3)},
            {new Num(4), new Num(5), new Num(6)},
            {new Num(7), new Num(8), new Num(9)}
        };
        Matrix instance = new Matrix(arr);
        Matrix doble = instance.add(instance);
        Num[][] arr2 = {
            {new Num(2), new Num(4), new Num(6)},
            {new Num(8), new Num(10), new Num(12)},
            {new Num(14), new Num(16), new Num(18)}
        };
        Matrix m = new Matrix<>(arr2);
        System.out.println(doble);
        System.out.println(m);
        assertEquals(doble, m);
    }

    /**
     * Test of toString method, of class Matrix.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Num[][] arr = {
            {new Num(1), new Num(2), new Num(3)},
            {new Num(4), new Num(5), new Num(6)},
            {new Num(7), new Num(8), new Num(9)}
        };
        Matrix instance = new Matrix(arr);
        String expResult = "Matrix{matrix=[[1, 2, 3], [4, 5, 6], [7, 8, 9]]}";
        String result = instance.toString();
        assertEquals(expResult, result);

        instance = new Matrix(4, new Num(0));
        expResult = "Matrix{matrix=[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]}";
        result = instance.toString();
        assertEquals(expResult, result);
    }

}
