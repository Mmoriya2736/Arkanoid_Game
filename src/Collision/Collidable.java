package Collision;

import Geometry.Point;
import Geometry.Rectangle;
import Sprite.Ball;

/**
 * The Collision.Collidable interface represents an object that can be involved in a collision.
 * Implementing classes must define the shape of the object for collision detection and
 * provide behavior when a collision occurs.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     * This method provides the geometric representation of the object for collision detection purposes.
     *
     * @return The rectangle representing the collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Calculates the new velocity of a ball after a collision at a given point.
     *
     * @param hitter           the ball that hit the object
     * @param collisionPoint   the point at which the collision occurred
     * @param currentVelocity  the current velocity of the ball before the collision
     * @return the new velocity of the ball after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
    /**
     * Returns whether the object is dynamic (i.e., can move or change over time).
     *
     * @return if the object is dynamic; {@code false} otherwise.
     */
    boolean isDynamic();
}
