/**
 * @File: TestTileT.java
 * @Author: Jie Zhang - zhanj265
 * @Date: April.13th, 2021
 * @Description: tests for module TileT
 */

package src;


import org.junit.*;
import static org.junit.Assert.*;

public class TestTileT{
    private TileT t1;
    private TileT t2;
    private TileT t3;

    @Before
    public void setUp(){
        t1 = new TileT();
        t2 = new TileT(4);
        t3 = new TileT(256);
    }

    @After
    public void tearDown(){
        t1 = null;
        t2 = null;
        t3 = null;
    }

    @Test
    public void testGetValue(){
        assertTrue(t1.getValue() == 0);
        assertTrue(t2.getValue() == 4);
        assertTrue(t3.getValue() == 256);
    }

    @Test
    public void testSetValue(){
        t1.setValue(8);
        assertTrue(t1.getValue() == 8);
        t2.setValue(0);
        assertTrue(t2.getValue() == 0);
    }

    @Test
    public void testToString(){
        assertEquals(t1.toString(), "0");
        assertEquals(t2.toString(), "4");
        assertEquals(t3.toString(), "256");
    }
}