package Geometry;

import java.util.ArrayList;
import java.util.List;
import Game.Tools;
/**
 * The Geometry.Rectangle class represents a geometric rectangle defined by its upper-left corner, width, and height.
 * It provides methods for handling geometric operations such as intersection detection with lines.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line left;
    private Line right;
    private Line top;
    private Line bottom;
    /**
     * Creates a new rectangle given its upper-left point, width, and height.
     * It also computes the four sides of the rectangle (left, right, top, bottom).
     *
     * @param upperLeft The upper-left corner of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * Returns a list of intersection points between this rectangle and a specified line.
     * The list will contain all intersection points with the four sides of the rectangle.
     *
     * @param line The line to check for intersections with the rectangle
     * @return A list of points where the given line intersects the rectangle
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        left = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
        right = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        top = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        bottom = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        List<Point> list = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();
        lines.add(left);
        lines.add(right);
        lines.add(top);
        lines.add(bottom);
        for (Line edge : lines) {
            if (line.isIntersecting(edge)) {
                Point intersection = line.intersectionWith(edge);
                if (intersection != null) {
                    list.add(intersection);
                }
            }
        }
        return list;
    }
    /**
     * Returns the width of the rectangle.
     *
     * @return The width of the rectangle
     */
    public double getWidth() {
        return width;
    }
    /**
     * Returns the height of the rectangle.
     *
     * @return The height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return The upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Sets the upper-left point of the rectangle.
     *
     * @param upperLeft The new upper-left point to set for the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Returns the left edge of the rectangle.
     *
     * @return The left edge of the rectangle
     */
    public Line getLeft() {
        return left;
    }

    /**
     * Returns the right edge of the rectangle.
     *
     * @return The right edge of the rectangle
     */
    public Line getRight() {
        return right;
    }

    /**
     * Returns the top edge of the rectangle.
     *
     * @return The top edge of the rectangle
     */
    public Line getTop() {
        return top;
    }

    /**
     * Returns the bottom edge of the rectangle.
     *
     * @return The bottom edge of the rectangle
     */
    public Line getBottom() {
        return bottom;
    }
    /**
     * Sets the dimensions and position of the rectangle.
     *
     * @param upperLeft the upper-left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public void setRectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * Determines whether a given point is inside or on the edge of the rectangle.
     *
     * @param center the point to check.
     * @return {@code true} if the point is within the bounds of the rectangle (including edges),
     *         {@code false} otherwise.
     */
    public boolean contains(Point center) {
        double x = center.getX();
        double y = center.getY();
        double left = this.upperLeft.getX();
        double right = left + this.width;
        double top = this.upperLeft.getY();
        double bottom = top + this.height;
        return (x >= left || Tools.doubleEquals(x, left)) && (x <= right || Tools.doubleEquals(x, right))
                && (y >= top || Tools.doubleEquals(y, top)) && (y <= bottom || Tools.doubleEquals(y, bottom));
    }
}
