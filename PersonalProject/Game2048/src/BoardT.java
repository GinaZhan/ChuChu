/**
 * @File: BoardT.java
 * @Author: Jie Zhang - zhanj265
 * @Date: April.13th, 2021
 * @Description: a module for storing the state and status of the game
 */

package src;

import java.util.ArrayList;
import java.util.Random;

public class BoardT{
    // State Variables;
    private TileT[][] board;
    private int stop;

    // Exported constants
    public final static int size = 4;

    /**
     * @brief constructor
     * @details generates an empty board
     */
    public BoardT(){
        board = new TileT[size][];
        for (int x = 0; x < size; x++) {
            board[x] = new TileT[size];
            for (int y = 0; y < size; y++) {
                board[x][y] = new TileT();
            }
        }
        stop = 0;
    }

    /**
     * @brief constructor
     * @details User can choose to use their own board. Also easier for testing since the values are not randomized
     * @param b - a predefined board
     */
    public BoardT(TileT[][] b){
        board = b;
        stop = 0;
    }

    // to test whether a position is valid in this board
    private boolean validateCell(int x, int y){
        if (x >= size || y >= size || x < 0 || y < 0) 
            return false;
        return true;
    }

    /**
     * @brief Set a 2 or 4 to a random empty space in board
     * @details 90% possibility to be 2, 10% possibility to be 4; if no empty
     *          space, do nothing
     */
    public void setRandom(){
        ArrayList<int[]> free = new ArrayList<int[]>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                TileT current = board[x][y];
                if (current.getValue() == 0) {
                    free.add(new int[] {x, y});
                }
            }
        }
        int len = free.size();
        if (len == 0)
            return;
        Random generator = new Random();
        int position = generator.nextInt(len);
        int twoOrFour = generator.nextInt(10);
        int[] cell = free.get(position);
        int x = cell[0];
        int y = cell[1];
        if (twoOrFour == 0) {
            setTile(x, y, 4);
        } else {
            setTile(x, y, 2);
        }
    }

    /**
     * @brief Change the value of a centain tile in board
     * @param x index of row
     * @param y index of column
     * @param t new value set to the tile
     * @throws IndexOutOfBoundsException if the position is invalid in board
     */
    public void setTile(int x, int y, int t){
        if (!validateCell(x, y))
            throw new IndexOutOfBoundsException("Invalid coordinate");
        board[x][y].setValue(t);
    }

    // help to move a tile at a certain position vertically
    private void verticalMove(int x, int y, String direction){
        TileT current = board[x][y];
        TileT destination = board[stop][y];
        if (stop == x)
            return;
        if (current.getValue() == 0)
            return;
        if (destination.getValue() == 0) {
            setTile(stop, y, current.getValue());
            setTile(x, y, 0);
            return;
        } 
        if (destination.getValue() == current.getValue()) {
            int out = destination.getValue() + current.getValue();
            setTile(stop, y, out);
            setTile(x, y, 0);
            if (direction.equals("up")) {
                stop += 1;
            } else {
                stop -= 1;
            }
            return;
        }
        if (direction.equals("up")) {
            stop += 1;
        } else {
            stop -= 1;
        }
        this.verticalMove(x, y, direction);
    }

    /**
     * @brief move all tiles upward and tiles with same value can merge
     */
    public void up(){
        for (int y = 0; y < size; y++ ) {
            stop = 0;
            for (int x = 0; x < size; x++ ) {
                verticalMove(x, y, "up");
            }
        }
    }

    /**
     * @brief move all tiles downward and tiles with same value can merge
     */
    public void down(){
        for (int y = 0; y < size; y++ ) {
            stop = size - 1;
            for (int x = size - 1; x >= 0; x-- ) {
                verticalMove(x, y, "down");
            }
        }
    }

    // help to move a tile at a certain position vertically
    private void horizontalMove(int x, int y, String direction){
        TileT current = board[x][y];
        TileT destination = board[x][stop];
        if (stop == y)
            return;
        if (current.getValue() == 0)
            return;
        if (destination.getValue() == 0) {
            setTile(x, stop, current.getValue());
            setTile(x, y, 0);
            return;
        } 
        if (destination.getValue() == current.getValue()) {
            int out = destination.getValue() + current.getValue();
            setTile(x, stop, out);
            setTile(x, y, 0);
            if (direction.equals("left")) {
                stop += 1;
            } else {
                stop -= 1;
            }
            return;
        }
        if (direction.equals("left")) {
            stop += 1;
        } else {
            stop -= 1;
        }
        this.horizontalMove(x, y, direction);
    }

    /**
     * @brief move all tiles to left and tiles with same value can merge
     */
    public void left(){
        for (int x = 0; x < size; x++ ) {
            stop = 0;
            for (int y = 0; y < size; y++ ) {
                horizontalMove(x, y, "left");
            }
        }
    }

    /**
     * @brief move all tiles to right and tiles with same value can merge
     */
    public void right(){
        for (int x = 0; x < size; x++ ) {
            stop = size - 1;
            for (int y = size - 1; y >= 0; y-- ) {
                horizontalMove(x, y, "right");
            }
        }
    }

    /**
     * @brief determine whether user fail this game
     * @details game is over when no tiles can move to make this board change
     * @return true if the game is over; otherwise, return false
     */
    public boolean gameOver(){
        for (int x = 0; x < size - 1; x++) {
            for (int y = 0; y < size - 1; y++) {
                if (board[x][y].getValue() == 0) {
                    return false;
                }
                if (board[x][y].getValue() == board[x + 1][y].getValue() || board[x][y].getValue() == board[x][y + 1].getValue()) {
                    return false;
                }
            }
        }
        for (int y = 0; y < size - 1; y++) {
            if (board[size - 1][y].getValue() == 0) {
                return false;
            }
            if (board[size - 1][y].getValue() == board[size - 1][y + 1].getValue()) {
                return false;
            }
        }
        for (int x = 0; x < size - 1; x++) {
            if (board[x][size - 1].getValue() == 0) {
                return false;
            }
            if (board[x][size - 1].getValue() == board[x + 1][size - 1].getValue()) {
                return false;
            }
        }
        if (board[size - 1][size - 1].getValue() == 0) {
            return false;
        }
        return true;
    }

    /**
     * @brief find the largest value all tiles have in board
     * @return the largest value in this board's tiles
     */
    public int bestTile(){
        int out = 0;
        for (int x = 0; x < size; x++ ) {
            for (int y = 0; y < size; y++ ) {
                if (board[x][y].getValue() > out) {
                    out = board[x][y].getValue();
                }
            }
        }
        return out;
    }

    /**
     * @brief return the value of a certain tile in board as a string
     * @param x index of row
     * @param y index of column
     * @return the value of the tile as a string
     * @throws IndexOutOfBoundsException if the position is invalid
     */
    public String getTile(int x, int y){
        if (!validateCell(x, y))
            throw new IndexOutOfBoundsException("Invalid coordinate");
        return board[x][y].toString();
    }

    /**
     * @brief checks whether two boardT are equal
     * @details if all tiles in the same position have the same value in their
     *          boards, the two BoardT objects are equal
     * @param b a BoardT object
     * @return true if two BoardT objects are equal; otherwise, return false
     */
    public boolean equals(BoardT b){
        for (int x = 0; x < size; x++ ) {
            for (int y = 0; y < size; y++ ) {
                if (!board[x][y].toString().equals(b.getTile(x, y))) {
                    return false;
                }
            }
        }
        return true;
    }
}