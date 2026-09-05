package Geometry;

import Game.Tools;

import java.util.List;

/**
 * The Geometry.Line class represents a line segment in a 2D Cartesian coordinate system.
 * It provides methods for calculating length, middle point, slope, intercept,
 * and checking intersections between lines.
 */
public class Line {
    // Fields
    private Point start;
    private Point end;

    /**
     * Constructs a Geometry.Line with two given endpoints.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Geometry.Line using coordinates of two endpoints.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return The Euclidean distance between the start and end points.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return A Geometry.Point representing the midpoint of the line.
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return The starting Geometry.Point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return The ending Geometry.Point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * Calculates and returns the slope of the line.
     *
     * @return The slope of the line.
     */
    public double getSlope() {
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }
    /**
     * Calculates and returns the y-intercept of the line.
     *
     * @return The y-intercept of the line.
     */
    public double getIntercept() {
        return start.getY() - getSlope() * start.getX();
    }
    /**
     * Checks if the line is vertical by comparing the x-coordinates of its start and end points.
     *
     * @return true if the line is vertical (has the same x-coordinate for start and end points),
     *         false otherwise.
     */
    public boolean ifVertical() {
        return Tools.doubleEquals(start.getX(), end.getX());
    }
    /**
     * Checks if two vertical lines intersect by comparing their x-coordinates and y-ranges.
     *
     * @param other The other vertical line to check for intersection.
     * @return true if the vertical lines intersect (share the same x-coordinate
     *         and have overlapping y-ranges), false otherwise.
     */
    public boolean isIntersectVerticals(Line other) {
        if (Tools.doubleEquals(start.getX(), other.start.getX())) {
            if (start.getY() > other.start.getY() && end.getY() > other.start.getY()
                    && start.getY() > other.end.getY() && end.getY() > other.end.getY()) {
                return false;
            }
            if (start.getY() < other.start.getY() && end.getY() < other.start.getY()
                    && start.getY() < other.end.getY() && end.getY() < other.end.getY()) {
                return false;
            }
            return true;
        }
        return false;
    }
    /**
     * Checks if a vertical line intersects with another line by comparing x-coordinates.
     *
     * @param other The line to check for intersection with the vertical line.
     * @return true if the vertical line crosses the x-coordinate range of the other line,
     *         false otherwise.
     */
    public boolean isIntersectingVertical(Line other) {
        if ((other.start.getX() <= start.getX() && start.getX() <= other.end.getX())
                || (other.start.getX() >= start.getX() && start.getX() >= other.end.getX())) {
            double y = other.getSlope() * start.getX() + other.getIntercept();
            if ((start.getY() <= y && y <= end.getY()) || (start.getY() >= y && y >= end.getY())
                    || Tools.doubleEquals(start.getY(), y) || Tools.doubleEquals(end.getY(), y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this line segment overlaps with another line segment on the same line.
     * The overlap is determined based on the x-coordinates of the start and end points of both lines.
     *
     * @param other The other {@code Geometry.Line} to compare with.
     * @return {@code true} if the two lines overlap on the x-axis, {@code false} otherwise.
     */
    public boolean isSameLineOverlap(Line other) {
        // Check if they have the same slope and intercept (i.e., are collinear)
        if (!Tools.doubleEquals(this.getSlope(), other.getSlope())
                || !Tools.doubleEquals(this.getIntercept(), other.getIntercept())) {
            return false;
        }

        // Check if they actually overlap in more than one point
        double minX1 = Math.min(start.getX(), end.getX());
        double maxX1 = Math.max(start.getX(), end.getX());
        double minX2 = Math.min(other.start.getX(), other.end.getX());
        double maxX2 = Math.max(other.start.getX(), other.end.getX());

        double minY1 = Math.min(start.getY(), end.getY());
        double maxY1 = Math.max(start.getY(), end.getY());
        double minY2 = Math.min(other.start.getY(), other.end.getY());
        double maxY2 = Math.max(other.start.getY(), other.end.getY());

        boolean xOverlap = !(maxX1 < minX2 || minX1 > maxX2); // if I need to change also here;
        boolean yOverlap = !(maxY1 < minY2 || minY1 > maxY2);

        return xOverlap && yOverlap;
    }
    /**
     * Checks if this line intersects with another line.
     * Handles special cases including:
     * - Vertical lines
     * - Parallel lines
     * - Lines with the same slope
     *
     * @param other The other line to check intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.ifVertical() && other.ifVertical()) {
            return isIntersectVerticals(other);
        }
        if (this.ifVertical()) {
            return this.isIntersectingVertical(other);
        }
        if (other.ifVertical()) {
            return other.isIntersectingVertical(this);
        }
        double m1 = this.getSlope();
        double m2 = other.getSlope();
        double b1 = this.getIntercept();
        double b2 = other.getIntercept();

        // If lines are parallel (same slope)
        if (Tools.doubleEquals(m1, m2)) {
            if (!Tools.doubleEquals(b1, b2)) {
                return false;
            }
            // Check x-coordinate overlap
            return isSameLineOverlap(other);
        }

        // Calculate intersection point
        double intersectX = (b2 - b1) / (m1 - m2);

        // Check if intersection x is within both line segments
        // Use <= and >= to include endpoint intersections
        boolean withinThisX = (intersectX >= Math.min(start.getX(), end.getX())
                && intersectX <= Math.max(start.getX(), end.getX()))
                || Tools.doubleEquals(intersectX, start.getX()) || Tools.doubleEquals(intersectX, end.getX());
        boolean withinOtherX = (intersectX >= Math.min(other.start.getX(), other.end.getX())
                && intersectX <= Math.max(other.start.getX(), other.end.getX()))
                || Tools.doubleEquals(intersectX, other.start.getX())
                || Tools.doubleEquals(intersectX, other.end.getX());


        return withinThisX && withinOtherX;
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 The first line to check for intersection.
     * @param other2 The second line to check for intersection.
     * @return true if both lines intersect with this line, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * Handles special cases including vertical lines and identical lines.
     *
     * @param other The other line to find intersection with.
     * @return A Geometry.Point representing the intersection or null if no intersection.
     */
    public Point intersectionWith(Line other) {
        if (other.equals(this)) {
            return null;
        }

        if (!isIntersecting(other)) {
            return null;
        }

        if (this.ifVertical() && other.ifVertical()) {
            if (Tools.doubleEquals(Math.max(start.getY(), end.getY()),
                    Math.min(other.start.getY(), other.end.getY()))) {
                return new Point(start.getX(), Math.max(start.getY(), end.getY()));
            }
            if (Tools.doubleEquals(Math.min(start.getY(), end.getY()),
                    Math.max(other.start.getY(), other.end.getY()))) {
                return new Point(start.getX(), Math.min(start.getY(), end.getY()));
            }
            //otherwise
            return null;
        }
        if (this.isSameLineOverlap(other)) {
            if (this.start.equals(other.start)) {
                return start;
            }
            if (this.end.equals(other.end)) {
                return end;
            }
            if (this.start.equals(other.end)) {
                return start;
            }
            if (this.end.equals(other.start)) {
                return end;
            }
            return null; // Only return null if they truly overlap (fixed method)
        }

        double x, y;
        if (this.ifVertical()) {
            x = this.start().getX();
            y = other.getSlope() * x + other.getIntercept();
        } else if (other.ifVertical()) {
            x = other.start().getX();
            y = this.getSlope() * x + this.getIntercept();
        } else {
            x = (other.getIntercept() - this.getIntercept()) / (this.getSlope() - other.getSlope());
            y = this.getSlope() * x + this.getIntercept();
        }
        return new Point(x, y);
    }
    /**
     * Compares this line with another line for equality.
     *
     * @param other The other Geometry.Line object to compare with.
     * @return true if the lines have the same start and end points, false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {  // Prevent null pointer exception
            return false;
        }
        return other.start() != null && other.end() != null
                && other.start().equals(this.start) && other.end().equals(this.end)
                || other.start().equals(this.end) && other.end().equals(this.start());
    }

    /**
     *
     * @param p distance from this line to p
     * @return the distance
     */
    public double distanceFromPoint(Point p) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double a = y2 - y1;
        double b = x1 - x2;
        double c = (x2 * y1) - (y2 * x1);

        return Math.abs(a * p.getX() + b * p.getY() + c) / Math.sqrt(a * a + b * b);
    }

    /**
     *
     * @param p check the closest point on the line to this point
     * @return the closest point on the line to p
     */
    public Point closestPointTo(Point p) {
        double x1 = this.start().getX();
        double y1 = this.start().getY();
        double x2 = this.end().getX();
        double y2 = this.end().getY();

        double px = p.getX();
        double py = p.getY();
        double dx = x2 - x1;
        double dy = y2 - y1;

        if (dx == 0 && dy == 0) {
            return new Point(x1, y1);
        }
        double t = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);
        t = Math.max(0, Math.min(1, t));
        double closestX = x1 + t * dx;
        double closestY = y1 + t * dy;

        return new Point(closestX, closestY);
    }
    /**
     * Determines the closest intersection point between the current line and the given rectangle.
     * If the line does not intersect with the rectangle, the method returns null.
     * Otherwise, it returns the closest intersection point to the start of the line.
     *
     * @param rect The rectangle to check for intersections with the line
     * @return The closest intersection point between the line and the rectangle, or null if no intersection exists
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        }

        Point closestPoint = list.get(0);
        double minDistance = this.start.distance(closestPoint);

        for (int i = 1; i < list.size(); i++) {
            double dist = this.start.distance(list.get(i));
            if (dist < minDistance) {
                minDistance = dist;
                closestPoint = list.get(i);
            }
        }
        return closestPoint;
    }

}



