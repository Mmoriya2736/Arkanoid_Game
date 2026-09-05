package Game;

import Sprite.Ball;
import Sprite.Block;

/**
 * The {@code ScoreTrackingListener} class is a {@link HitListener} that tracks and updates the score
 * whenever a block is hit by a ball. It adds a fixed number of points for each hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with a given counter to track the score.
     *
     * @param scoreCounter the counter used to store and update the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Called whenever a block is hit. Increases the score by a fixed amount (5 points).
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }

    /**
     * Returns the current score value.
     *
     * @return the current score
     */
    public int getValue() {
        return currentScore.getValue();
    }

    /**
     * Returns the score counter instance.
     *
     * @return the score counter
     */
    public Counter getCounter() {
        return currentScore;
    }
}
