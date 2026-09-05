package Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Collision.Collidable;
import Collision.Velocity;
import Game.Game;
import Geometry.Point;
import Geometry.Rectangle;
import Game.Tools;
import Game.HitNotifier;
import Game.HitListener;
import biuoop.DrawSurface;
/**
 * The Sprite.Sprite.Block class represents a block object in the game.
 * It is a collidable object that interacts with other objects in the game (e.g., the ball).
 * The block has a color and can be drawn on the screen. It handles collision logic by reversing
 * the direction of the object that collides with it based on the side of the block hit.
 */
public class Block extends Rectangle implements Collidable, Sprite, HitNotifier {
    private Color color;
    private boolean isDynamic = false;
    private List<HitListener> hitListeners;
    /**
     * Constructs a new Sprite.Sprite.Block object with the specified upper-left corner, width, and height.
     *
     * @param upperLeft The upper-left point of the block
     * @param width The width of the block
     * @param height The height of the block
     */
    public Block(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
        this.hitListeners = new ArrayList<>();
    }
    /**
     * Sets the color of the block.
     *
     * @param color The color to set for the block
     */
    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Block deathRegion = new Block(new Point(0, 580), 800, 20);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (Tools.doubleEquals(getLeft().start().getX(), collisionPoint.getX())
                || Tools.doubleEquals(getRight().start().getX(), collisionPoint.getX())) {
            dx = -dx;
        }
        if (Tools.doubleEquals(getTop().start().getY(), collisionPoint.getY())
        || Tools.doubleEquals(getBottom().start().getY(), collisionPoint.getY())) {
            dy = -dy;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return new Velocity(dx, dy);
    }
    @Override
    public void drawOn(DrawSurface d) {
        if (this.color != null) {  // Ensure color is set before drawing
            d.setColor(this.color);
        } else {
            // Default color in case no color was set
            d.setColor(Color.MAGENTA);
        }
        d.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) getWidth(), (int) getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) getWidth(), (int) getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }
    /**
     * Add this block to the game.
     * @param g the game to add the block to
     */
    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    @Override
    public boolean isDynamic() {
        return isDynamic;
    }
    /**
     * Checks if the given ball's color matches this object's color.
     *
     * @param ball the ball to compare color with
     * @return true if the ball's color matches this object's color; false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.color);
    }

    /**
     * Removes this object from the game by removing it from both the collidables and sprites collections.
     *
     * @param game the game instance from which this object should be removed
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Retrieves the color associated with this object.
     *
     * @return the color of this object.
     */
    public Color getColor() {
        return color;
    }

}
