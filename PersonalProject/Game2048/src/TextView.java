/**
 * @File: TextView.java
 * @Author: Jie Zhang - zhanj265
 * @Date: April.15th, 2021
 * @Description: a module for viewing the game in the form of text
 */

package src;

public class TextView{
    private static TextView visual = null;

    /** 
     * @brief constructor
     */
    private TextView(){}

    /**
     * @brief public static method for obtaining a single instance
     * @return a TextView object
     */
    public static TextView getInstance(){
        if (visual == null)
            return visual = new TextView();
        return visual;
    }

    /**
     * @brief display a welcome message
     */
    public void printWelcomeMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("               Welcome to Game 2048              ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a message to teach user how to play this game
     */
    public void printHowToPlay(){
        System.out.println("                     New game                    ");
        System.out.println("                 -----------------               ");
        System.out.println("Move the tiles");
        System.out.println("Tiles with the same number merge into one when they touch");
        System.out.println("When one tile reaches 2048, you win!");
        System.out.println("There are 4 directions:");
        System.out.println("w: UP  s:DOWN  a: LEFT  d:RIGHT");
        System.out.println("Enter e to exit");
    }

    /**
     * @brief Display the board on the screen
     * @param boardT - the game board
     */
    public void printBoard(BoardT board){
        int size = board.size;
        System.out.println();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                int space = 5;
                System.out.print(board.getTile(i, j));
                int len = board.getTile(i, j).length();
                for (int k = 0; k < space - len; k++){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * @brief display a message after the user win the game
     */
    public void printWinMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("            Congratulations! You win!            ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a message after the user fail this game
     */
    public void printFailMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("                Sorry! Game over!                ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a message after the game ends or the user exits the game
     */
    public void printEndingMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("             Thank You For Playing !!!           ");
        System.out.println("-------------------------------------------------");
    }
}