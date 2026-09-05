package Game;

import Collision.Collidable;
import Collision.CollisionInfo;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;
/**
 * The Game.GameEnvironment class is responsible for managing all collidable objects in the game.
 * It provides methods to add collidables and check for the closest collision between a moving object and the
 * collidables.
 */
public class GameEnvironment {
    private List<Collidable> listCollide;
    /**
     * Constructs a new Game.GameEnvironment instance.
     * Initializes an empty list to hold the collidables in the environment.
     */
    public GameEnvironment() {
        listCollide = new ArrayList<>();
    }
    /**
     * Adds a collidable object to the environment.
     *
     * @param c The collidable object to add to the environment
     */
    public void addCollidable(Collidable c) {
        listCollide.add(c);
    }

    /**
     * Determines the closest collision between a moving object (represented by the given trajectory)
     * and any of the collidables in the environment.
     *
     * @param trajectory The trajectory (path) of the moving object, defined by a line from start to end
     * @return A Collision.CollisionInfo object that contains the closest collidable and the point of collision,
     *         or null if no collision is detected
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable = null;
        Point closestPoint = null;
        double closestDistance = -1;
        double distance;
        for (Collidable c : listCollide) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersection != null) {
                distance = trajectory.start().distance(intersection);
                if (distance < closestDistance || closestDistance == -1) {
                    closestDistance = distance;
                    closestCollidable = c;
                    closestPoint = intersection;
                }
            }
        }
        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestCollidable, closestPoint);
    }
    /**
     * Returns the list of collidable objects associated with this environment or entity.
     *
     * @return a list of {@link Collidable} objects.
     */
    public List<Collidable> getListCollide() {
        return listCollide;
    }
    /**
     * Removes a collidable object from the list of collidables.
     *
     * @param c the {@code Collidable} to remove
     */
    public void removeCollidable(Collidable c) {
        listCollide.remove(c);
    }

}