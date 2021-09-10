/**
 * @file: Demo.java
 * @Author: Jie Zhang
 * @Date: 15th April, 2021
 * @Description: Runs the game
 */

import src.BoardT;
import src.TileT;
import src.TextView;
import src.GameController;

public class Demo
{
	/**
	 * @brief to begin a new game and allow user to play it
	 */
   public static void main(String[] args) {

      BoardT boardT = new BoardT();
      TextView view = TextView.getInstance();
      GameController game = GameController.getInstance(boardT, view);
      game.runGame();
  }
}
