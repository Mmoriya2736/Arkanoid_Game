package Sprite;

import Game.Game;
import biuoop.DrawSurface;

import java.awt.Color;
import Game.Counter;
/**
 * This class represents a score indicator that displays the current score on the screen.
 * It implements the Sprite interface and is added to the game to visually update the score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * Constructs a new ScoreIndicator with the given score counter.
     *
     * @param score the counter object that holds the score value
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(395, 15, String.valueOf(score.getValue()), 15);
    }
    @Override
    public void timePassed() {
    }
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
