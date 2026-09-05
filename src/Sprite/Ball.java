package Sprite;

import Collision.Collidable;
import Collision.CollisionInfo;
import Collision.Velocity;
import Game.Game;
import Geometry.Line;
import Geometry.Point;
import Game.GameEnvironment;
import biuoop.DrawSurface;

/**
 * The Sprite.Sprite.Ball class represents a ball with a center point, radius, color, and velocity.
 * It can move, detect collisions with edges and lines, and draw itself on a surface.
 */
public class Ball implements Sprite {
    //fields
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment environment;

    /**
     * Constructs a ball with the given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructs a ball with the given x and y coordinates, radius, and color.
     *
     * @param x     the x-coordinate of the ball's center
     * @param y     the y-coordinate of the ball's center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * @return the x-coordinate of the ball's center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the y-coordinate of the ball's center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the radius of the ball
     */
    public int getSize() {
        return r;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * Sets the color of the object.
     *
     * @param color the {@link java.awt.Color} to set
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }


    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }
    /**
     * Sets the game environment for the ball.
     * The game environment contains all the collidable objects that the ball
     * can interact with. This method allows the ball to be aware of its surroundings
     * so it can detect and respond to collisions during movement.
     *
     * @param environment the Game.GameEnvironment instance to associate with the ball
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }
    /**
     * Add this ball to the game.
     * @param g the game to add the ball to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball using dx and dy.
     *
     * @param dx change in x
     * @param dy change in y
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return the current velocity of the ball
     */
    public Velocity getVelocity() {
        if (v == null) {
            return new Velocity(0, 0);
        }
        return v;
    }
    /**
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Sets the center point of the ball.
     *
     * @param center the new center point
     */
    public void setCenter(Point center) {
        this.center = center;
    }
    /**
     * Moves the ball one step according to its velocity, handling collisions with objects
     * in the game environment.
     */
    public void moveOneStep() {
        double dx = v.getDx();
        double dy = v.getDy();
        double speed = Math.sqrt(dx * dx + dy * dy);
        double moveBackX = v.getDx() / speed * 0.1;
        double moveBackY = v.getDy() / speed * 0.1;
        int subSteps = 50;
        double subDx = this.v.getDx() / subSteps;
        double subDy = this.v.getDy() / subSteps;
        for (int i = 0; i < subSteps; i++) {
            Point endPoint = new Point(this.center.getX() + subDx, this.center.getY() + subDy);
            Line trajectory = new Line(this.center, endPoint);
            CollisionInfo collision = this.environment.getClosestCollision(trajectory);
            if (collision != null) {
                Point collisionPoint = collision.collisionPoint();
                Point newCenter = new Point(collisionPoint.getX() - moveBackX, collisionPoint.getY() - moveBackY);
                Velocity newV = collision.collisionObject().hit(this, collisionPoint, this.v);
                this.setVelocity(newV);
                this.setCenter(newCenter);
                break;
            } else {
                for (Collidable c : this.environment.getListCollide()) {
                    if (c.isDynamic() && c.getCollisionRectangle().contains(this.center)) {
                        Velocity v = c.hit(this, this.center, this.getVelocity());
                        this.setVelocity(v);
                        double y = c.getCollisionRectangle().getUpperLeft().getY() - 10;
                        this.center = new Point(this.center.getX(), y);
                        return;
                    } else {
                        this.setCenter(endPoint);
                    }
                }
            }
        }
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Removes this object from the game.
     *
     * @param g the game instance from which this object should be removed
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

}
