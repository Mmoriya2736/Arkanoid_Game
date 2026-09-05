package Game;

import Geometry.Point;

/**
 * Utility class containing helper methods.
 */
public class Tools {
    /**
     * Compares two double values for equality with a precision threshold.
     * This method checks if the absolute difference between the two values
     * is smaller than a predefined comparison threshold.
     *
     * @param a The first double value.
     * @param b The second double value.
     * @return true if the difference between the two values is within the threshold, false otherwise.
     */
    public static boolean doubleEquals(double a, double b) {
        return  Math.abs(a - b) < Point.COMPARISON_THRESHOLD;
    }
}
