package Collision;

import Geometry.Point;

/**
 * Represents information about a collision between an object and a collidable.
 * Contains the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Collidable collidable;
    private Point collisionPoint;

    /**
     * Constructs a Collision.CollisionInfo object with the specified collidable and collision point.
     *
     * @param collidable the collidable object involved in the collision
     * @param collisionPoint the point at which the collision occurs
     */
    public CollisionInfo(Collidable collidable, Point collisionPoint) {
        this.collidable = collidable;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}