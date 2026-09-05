package Game;

/**
 * The {@code HitNotifier} interface should be implemented by objects that can be hit and
 * want to notify registered listeners about hit events.
 */
public interface HitNotifier {
    /**
     * Adds a {@link HitListener} to the list of listeners to be notified of hit events.
     *
     * @param hl the hit listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a {@link HitListener} from the list of listeners to hit events.
     *
     * @param hl the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}
