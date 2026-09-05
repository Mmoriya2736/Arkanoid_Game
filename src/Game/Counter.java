package Game;

/**
 * The {@code Counter} class is a simple utility for keeping track of a numeric count.
 * It can be increased, decreased, and queried for its current value.
 */
public class Counter {
    private int count = 0;

    /**
     * Increases the current count by a specified number.
     *
     * @param number the number to add to the current count
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Decreases the current count by a specified number.
     *
     * @param number the number to subtract from the current count
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Returns the current value of the count.
     *
     * @return the current count
     */
    public int getValue() {
        return count;
    }

    /**
     * Returns the current {@code Counter} object.
     * This method may be useful when passing counters to other classes.
     *
     * @return this {@code Counter} instance
     */
    public Counter getCounter() {
        return this;
    }
}
