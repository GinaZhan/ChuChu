/**
 * @file: GameController.java
 * @Author: Jie Zhang
 * @Date: 15th April, 2021
 * @Description: controller module that handles inputs and links model and view module
 */

package src;

import java.util.Scanner;

public class GameController{

    // Define state variables
    private BoardT model;
    private TextView view;
    private static GameController controller = null;

    // Define environment variable
    private Scanner keyboard = new Scanner(System.in);

    /**
     * @brief constructor
     * @param model - model module (BoardT)
     * @param view - view module (TextView)
     */
    private GameController(BoardT model, TextView view){
        this.model = model;
        this.view = view;
    }

    /**
     * @brief public static method for obtaining a single instance
     * @param model a BoardT to store state and status of this game
     * @param view to allow user to view this game
     * @return the single GameController object
     */
    public static GameController getInstance(BoardT model, TextView view) 
    { 
        if (controller == null) 
            controller = new GameController(model, view); 
  
        return controller; 
    } 

    /**
     * @brief initialize the game 2048
     */
    public void initializeGame(){
        this.model = new BoardT();
        model.setRandom();
        model.setRandom();
    }

    /**
     * @brief get input from the user to move tiles to a direction or exit the game
     * @return the input
     * @throws IllegalArgumentException if the input is not acceptable
     */
    public String readDirection(){
        String input = "";
        input = keyboard.nextLine();
        if (!input.equals("w") && !input.equals("s") && !input.equals("a") && !input.equals("d") && !input.equals("e"))
            throw new IllegalArgumentException("Invalid Input");
        return input;
    }

    /**
     * 
     * @return gets the game status from the board to know whether to end this game
     */
    public boolean getStatus(){
        return this.model.gameOver();
    }

    /**
     * @brief move tiles in model to a direction given by user based on rules
     * @param dir - the direction tiles in model are moving to
     */
    public void move(String dir){
        if (dir.equals("w")) model.up();
        if (dir.equals("s")) model.down();
        if (dir.equals("a")) model.left();
        if (dir.equals("d")) model.right();
    }

    /**
     * @brief updates the view module to display a welcome message
     */
    public void displayWelcomeMessage(){
        view.printWelcomeMessage();
    }

    /**
     * @brief updates the view module to teach user how to play the game
     */
    public void displayGuideMessage(){
        view.printHowToPlay();
    }

    /**
     * @brief updates the view module to display the board
     */
    public void displayBoard(){
        view.printBoard(model);
    }

    /**
     * @brief updates the view module to display a win message
     */
    public void displayWinMessage(){
        view.printWinMessage();
    }

    /**
     * @brief updates the view module to display a fail message
     */
    public void displayFailMessage(){
        view.printFailMessage();
    }

    /**
     * @brief updates the view module to display a ending message
     */
    public void displayEnding(){
        view.printEndingMessage();
    }

    /**
     * @brief runs the game
     */
    public void runGame(){
        String input = "";
        displayWelcomeMessage();
        displayGuideMessage();
        initializeGame();
        System.out.println();
        displayBoard();
        try{
            input = readDirection();
            while (getStatus() == false && !(input.equals("e")) && model.bestTile() < 2048){
                move(input);
                model.setRandom();
                displayBoard();
                input = "";
                input = readDirection();
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Invalid Input");
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid Input");
        }
        if (model.bestTile() == 2048){
            displayWinMessage();
            displayEnding();
            System.exit(0);
        }
        if (getStatus() == true){
            displayFailMessage();
            displayEnding();
            System.exit(0);
        }
        displayEnding();
        System.exit(0);
    }
}