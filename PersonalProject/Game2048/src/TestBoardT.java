/**
 * @File: TestTileT.java
 * @Author: Jie Zhang - zhanj265
 * @Date: April.13th, 2021
 * @Description: tests for module TileT
 */

package src;


import org.junit.*;
import static org.junit.Assert.*;

public class TestBoardT{
    private BoardT board1;
    private BoardT board2;

    @Before
    public void setUp(){
        TileT[][] input1 = {{new TileT(2), new TileT(2), new TileT(2), new TileT()},
                            {new TileT(2), new TileT(8), new TileT(4), new TileT()},
                            {new TileT(), new TileT(4), new TileT(8), new TileT(2)},
                            {new TileT(16), new TileT(4), new TileT(2), new TileT(2)}};
        board1 = new BoardT(input1);
        TileT[][] input2 = {{new TileT(2), new TileT(4), new TileT(2), new TileT(8)},
                            {new TileT(16), new TileT(8), new TileT(4), new TileT(2)},
                            {new TileT(32), new TileT(256), new TileT(32), new TileT(16)},
                            {new TileT(2), new TileT(4), new TileT(16), new TileT(4)}};
        board2 = new BoardT(input2);
    }

    @After
    public void tearDown(){
        board1 = null;
        board2 = null;
    }

    @Test
    public void testSetRandom1(){
        board1.setRandom();
        board1.setRandom();
        board1.setRandom();
        assertFalse(board1.getTile(0, 3).equals("0"));
        assertFalse(board1.getTile(1, 3).equals("0"));
        assertFalse(board1.getTile(2, 0).equals("0"));
    }

    @Test
    public void testSetRandom2(){
        TileT[][] input = {{new TileT(2), new TileT(4), new TileT(2), new TileT(8)},
                           {new TileT(16), new TileT(8), new TileT(4), new TileT(2)},
                           {new TileT(32), new TileT(256), new TileT(32), new TileT(16)},
                           {new TileT(2), new TileT(4), new TileT(16), new TileT(4)}};
        BoardT board = new BoardT(input);
        board2.setRandom();
        assertTrue(board.equals(board2));
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testSetTile1(){
        board1.setTile(1, 4, 7);
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testSetTile2(){
        board1.setTile(-8, 2, 8);
    }

    @Test
    public void testSetTile3(){
        board1.setTile(3, 3, 8);
        assertTrue(board1.getTile(3, 3).equals("8"));
        board1.setTile(1, 2, 8);
        assertTrue(board1.getTile(1, 2).equals("8"));
        board2.setTile(0, 2, 16);
        assertTrue(board2.getTile(0, 2).equals("16"));
    }

    @Test
    public void testUp(){
        board1.up();
        TileT[][] input = {{new TileT(4), new TileT(2), new TileT(2), new TileT(4)},
                           {new TileT(16), new TileT(8), new TileT(4), new TileT()},
                           {new TileT(), new TileT(8), new TileT(8), new TileT()},
                           {new TileT(), new TileT(0), new TileT(2), new TileT()}};
        BoardT board = new BoardT(input);
        assertTrue(board1.equals(board));
    }

    @Test
    public void testDown(){
        board1.down();
        TileT[][] input = {{new TileT(), new TileT(), new TileT(2), new TileT()},
                           {new TileT(), new TileT(2), new TileT(4), new TileT()},
                           {new TileT(4), new TileT(8), new TileT(8), new TileT()},
                           {new TileT(16), new TileT(8), new TileT(2), new TileT(4)}};
        BoardT board = new BoardT(input);
        assertTrue(board1.equals(board));
    }

    @Test
    public void testLeft(){
        board1.left();
        TileT[][] input = {{new TileT(4), new TileT(2), new TileT(), new TileT()},
                           {new TileT(2), new TileT(8), new TileT(4), new TileT()},
                           {new TileT(4), new TileT(8), new TileT(2), new TileT()},
                           {new TileT(16), new TileT(4), new TileT(4), new TileT()}};
        BoardT board = new BoardT(input);
        assertTrue(board1.equals(board));
    }

    @Test
    public void testRight(){
        board1.right();
        TileT[][] input = {{new TileT(), new TileT(), new TileT(2), new TileT(4)},
                           {new TileT(), new TileT(2), new TileT(8), new TileT(4)},
                           {new TileT(), new TileT(4), new TileT(8), new TileT(2)},
                           {new TileT(), new TileT(16), new TileT(4), new TileT(4)}};
        BoardT board = new BoardT(input);
        assertTrue(board1.equals(board));
    }

    @Test
    public void testGameOver(){
        assertTrue(board2.gameOver());
        assertFalse(board1.gameOver());
    }

    @Test
    public void testBestTile(){
        assertTrue(board1.bestTile() == 16);
        assertTrue(board2.bestTile() == 256);
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetTile(){
        board1.getTile(9, 2);
    }

    @Test
    public void testGetTile2(){
        assertTrue(board1.getTile(1, 1).equals("8"));
        assertTrue(board1.getTile(3, 2).equals("2"));
        assertTrue(board1.getTile(2, 0).equals("0"));
        assertTrue(board2.getTile(0, 2).equals("2"));
        assertTrue(board2.getTile(3, 0).equals("2"));
    }

    @Test
    public void testEquals(){
        TileT[][] input1 = {{new TileT(2), new TileT(2), new TileT(2), new TileT()},
                            {new TileT(2), new TileT(8), new TileT(4), new TileT()},
                            {new TileT(), new TileT(4), new TileT(8), new TileT(2)},
                            {new TileT(16), new TileT(4), new TileT(2), new TileT(2)}};
        BoardT board3 = new BoardT(input1);
        TileT[][] input2 = {{new TileT(2), new TileT(4), new TileT(2), new TileT(8)},
                            {new TileT(16), new TileT(8), new TileT(4), new TileT(2)},
                            {new TileT(32), new TileT(256), new TileT(2), new TileT(16)},
                            {new TileT(2), new TileT(4), new TileT(16), new TileT(4)}};
        BoardT board4 = new BoardT(input2);
        assertTrue(board1.equals(board3));
        assertFalse(board2.equals(board4));
    }

}