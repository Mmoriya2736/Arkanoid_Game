package Sprite;

import Game.Game;
import biuoop.DrawSurface;
/**
 * The Sprite.Sprite interface represents an object in the game that can be drawn to the screen and updated over time.
 * Implementing classes should define how to draw themselves on the screen and how to handle updates in each game frame.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     * Implementing classes should define how the sprite should be rendered.
     *
     * @param d The DrawSurface to draw the sprite on
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that time has passed, allowing it to update its state.
     * This method is called periodically during the game loop.
     */
    void timePassed();
    /**
     * Adds this sprite to the given game.
     * The sprite will be registered so that it can be managed (drawn and updated) during the game loop.
     *
     * @param g The game to add the sprite to
     */
    void addToGame(Game g);
}