/******
 Name: moriya malkiel
 ID: 325875482
 Assignment: ass5
 *******/

import Game.Game;

/**
 * The Game.Game.Ass3Game class is the entry point of the game application.
 * It creates an instance of the Game.Game class, initializes it, and runs the game.
 * This class is responsible for launching the game logic.
 */
public class Ass5Game {
    /**
     * The main entry point for the Game.Game.Ass3Game application.
     * This method creates a new Game.Game object, initializes it, and then runs the game.
     *
     * @param args Command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
