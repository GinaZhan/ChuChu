/**
 * @File: TileT.java
 * @Author: Jie Zhang - zhanj265
 * @Date: April.13th, 2021
 * @Description: a module for tile type in the Game 2048
 */

package src;

public class TileT{
    // State variables
    // Assume the value is always 0 or the power of 2 (not included 1)
    private int value;

    /**
     * @brief constructor
     * @details generate a TileT with value 0
     */
    public TileT(){
        value = 0;
    }

    /**
     * @brief constructor
     * @details generate a TileT with given value
     * @param num a given natrual number
     */
    public TileT(int num){
        value = num;
    }

    /**
     * @brief get the value of the TileT
     * @return the value of the TileT
     */
    public int getValue() {
        return value;
    }

    /**
     * @brief set the value of the TileT to be the new number
     * @param num the new value set to TileT
     */
    public void setValue(int num) {
        value = num;
    }

    /**
     * @brief convert the value to string
     * @return the value of the TileT as a string
     */
    public String toString(){
        Integer i = new Integer(value);
        return i.toString();
    }
}