package Game;

import Sprite.Ball;
import Sprite.Block;

/**
 * A {@code BlockRemover} is in charge of removing blocks from the game and
 * keeping count of the number of blocks that remain.
 * It implements the {@link HitListener} interface to respond to hit events.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    /**
     * Constructs a BlockRemover with the specified game and block counter.
     *
     * @param game the game instance from which blocks will be removed
     * @param remainingBlocks the counter tracking how many blocks remain
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * This method is called when a block is hit. It removes the block from the game,
     * unregisters this listener from the block, and decreases the block counter.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
