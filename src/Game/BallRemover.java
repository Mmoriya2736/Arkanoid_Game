package Game;

import Sprite.Ball;
import Sprite.Block;
/**
 * The {@code BallRemover} class is a hit listener that is responsible for removing
 * balls from the game when they hit a designated block (usually the bottom boundary).
 * It also keeps track of the remaining number of balls in the game.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a {@code BallRemover} with a reference to the game and a counter
     * for the remaining balls.
     *
     * @param game the game instance from which balls will be removed
     * @param remainingBalls a counter tracking the number of balls left in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever a ball hits a block that is designated for ball removal.
     * It removes the ball from the game and decreases the ball counter.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(game);
    }
}
