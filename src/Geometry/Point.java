package Geometry;

import Game.Tools;

/**
 * The Geometry.Point class represents a point in a 2D Cartesian coordinate system.
 * It provides methods to calculate the distance between two points and to check equality.
 */
public class Point {
    public static final double COMPARISON_THRESHOLD = 0.0000001;
    // Fields
    private double x;
    private double y;

    /**
     * Constructs a Geometry.Point object with specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other Geometry.Point object.
     * @return The Euclidean distance between the two points.
     */
    public double distance(Point other) {
        return (Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)));
    }
    /**
     * Compares this point with another point to check if they are equal.
     *
     * @param other The other Geometry.Point object to compare with.
     * @return true if both points have the same coordinates, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Tools.doubleEquals(x, other.x) && Tools.doubleEquals(y, other.y);
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }
}

