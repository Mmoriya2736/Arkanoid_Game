package Sprite;

import Collision.Collidable;
import Collision.Velocity;
import Game.Game;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * The Sprite.Sprite.Paddle class represents the paddle used in the game.
 * It is responsible for moving left and right based on user input, drawing itself on the screen,
 * handling collisions, and interacting with the game.
 */
public class Paddle extends Rectangle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private boolean isDynamic = true;
    /**
     * Constructs a new Sprite.Sprite.Paddle object with the specified upper-left corner, width, and height.
     *
     * @param upperLeft The upper-left point of the paddle
     * @param width The width of the paddle
     * @param height The height of the paddle
     */
    public Paddle(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }
    /**
     * Sets the KeyboardSensor to be used for detecting user input.
     *
     * @param keyboard The KeyboardSensor to set
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }
    /**
     * Moves the paddle to the left by 5 units, ensuring it doesn't go beyond the left boundary.
     */
    public void moveLeft() {
        double x = getUpperLeft().getX() - 5;
        double y = getUpperLeft().getY();
        if (x < 20) {
            x = 800 - 120;
        }
        this.setUpperLeft(new Point(x, y));
        setRectangle(getUpperLeft(), getWidth(), getHeight());
    }
    /**
     * Moves the paddle to the right by 5 units, ensuring it doesn't go beyond the right boundary.
     */
    public void moveRight() {
        double x = getUpperLeft().getX() + 5;
        double y = getUpperLeft().getY();
        if (x > 800 - 120) {
            x = 20;
        }
        this.setUpperLeft(new Point(x, y));
        setRectangle(getUpperLeft(), getWidth(), getHeight());
    }

    // Sprite.Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) getWidth(), (int) getHeight());
    }

    // Collision.Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double speed = currentVelocity.getSpeed();
        // determine which side of the paddle was hit
        Rectangle rect = getCollisionRectangle();
        double epsilon = 0.1;
        // Top edge collision
        if (Math.abs(collisionPoint.getY() - rect.getUpperLeft().getY()) < epsilon) {
            // Divide paddle into 5 regions
            double paddleStart = rect.getUpperLeft().getX();
            double relativeHitPoint = collisionPoint.getX() - paddleStart;
            double regionSize = rect.getWidth() / 5;
            // Determine region (0-4)
            int region = (int) (relativeHitPoint / regionSize);
            region = Math.max(0, Math.min(4, region));
            switch (region) {
                case 0: return Velocity.fromAngleAndSpeed(210, speed);
                case 1: return Velocity.fromAngleAndSpeed(240, speed);
                case 2: return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                case 3: return Velocity.fromAngleAndSpeed(300, speed);
                case 4: return Velocity.fromAngleAndSpeed(330, speed);
                default: return Velocity.fromAngleAndSpeed(270, speed);
            }
        }
        // Bottom edge collision
        if (Math.abs(collisionPoint.getY() - (rect.getUpperLeft().getY() + rect.getHeight())) < epsilon) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // Left edge collision
        if (Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < epsilon) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // Right edge collision
        if (Math.abs(collisionPoint.getX() - (rect.getUpperLeft().getX() + rect.getWidth())) < epsilon) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
    }
    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    @Override
    public boolean isDynamic() {
        return isDynamic;
    }
}