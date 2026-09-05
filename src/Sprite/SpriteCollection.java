package Sprite;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * The Sprite.Sprite.SpriteCollection class is responsible for managing a collection of sprites.
 * It provides methods to add sprites, notify them when time has passed, and draw them on the given drawing surface.
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * Constructs a new Sprite.Sprite.SpriteCollection instance.
     * Initializes an empty list to hold the sprites.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }
    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to add to the collection
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Notifies all sprites in the collection that time has passed.
     * Calls the `timePassed()` method on each sprite.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }
    /**
     * Draws all sprites in the collection on the given drawing surface.
     * Calls the `drawOn(d)` method on each sprite to render it.
     *
     * @param d The drawing surface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
    /**
     * Removes the given sprite from the list of sprites in the game.
     * This will stop the sprite from being drawn or updated during the game.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

}