package Collision;

import Geometry.Point;

/**
 * Collision.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    // fields
    private double dx;
    private double dy;

    /**
     * Constructs a velocity with the given dx and dy.
     *
     * @param dx the change in x-axis
     * @param dy the change in y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the horizontal component of the velocity.
     *
     * @return the dx value
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets the vertical component of the velocity.
     *
     * @return the dy value
     */
    public double getDy() {
        return dy;
    }

    /**
     * Takes a point with position (x, y) and returns a new point
     * with position (x + dx, y + dy).
     *
     * @param p the original point
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Creates a Collision.Velocity instance from an angle and speed.
     * Angle is in degrees where 0 degrees is to the right.
     *
     * @param angle the direction angle in degrees
     * @param speed the speed (magnitude of velocity)
     * @return a new Collision.Velocity based on the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * Returns the speed (magnitude) of the velocity.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

}
