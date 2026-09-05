package Game;

import Sprite.Ball;
import Sprite.Block;

/**
 * The {@code HitListener} interface should be implemented by any class that wants to be
 * notified when a {@link Block} is hit by a {@link Ball}.
 */
public interface HitListener {
    /**
     * This method is called whenever the {@code beingHit} object is hit.
     * The {@code hitter} parameter is the {@link Ball} that's doing the hitting.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
