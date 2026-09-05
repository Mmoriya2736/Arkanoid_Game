package Game;

import Collision.Collidable;
import Geometry.Point;
import Sprite.Block;
import Sprite.Sprite;
import Sprite.SpriteCollection;
import Sprite.Ball;
import Sprite.Paddle;
import Sprite.ScoreIndicator;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Game.Game class represents the core of the game logic, handling game initialization, sprite and collidable
 * management,
 * and the game loop. It initializes the game environment, creates game elements like balls, blocks, and paddles, and
 * runs the animation loop.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private ScoreTrackingListener score;
    private ScoreIndicator scoreIndicator;
    /**
     * Adds a collidable object to the game's environment.
     *
     * @param c The collidable object to add to the environment
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * Adds a sprite object to the game's sprite collection.
     *
     * @param s The sprite object to add to the collection
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes the game by setting up the GUI, creating the game elements (balls, blocks, paddle), and adding them
     * to the game. The environment and sprite collections are also initialized.
     */
    public void initialize() {
        // Initialize collections
        this.gui = new GUI("My Game.Game", 800, 600);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        Counter scoreCounter = new Counter();
        this.score = new ScoreTrackingListener(scoreCounter);
        this.scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);
        // Create a ball and add it to the game
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);

        // Create and add boundaries as blocks
        Block topBoundary = new Block(new Point(0, 20), 800, 20);
        Block leftBoundary = new Block(new Point(0, 20), 20, 580);
        Block rightBoundary = new Block(new Point(780, 20), 20, 580);
        Block bottomBoundary = new Block(new Point(0, 580), 800, 20);

        // Set boundary colors
        topBoundary.setColor(Color.cyan);
        leftBoundary.setColor(Color.cyan);
        rightBoundary.setColor(Color.cyan);
        bottomBoundary.setColor(Color.cyan);

        // Add boundaries to the game
        topBoundary.addToGame(this);
        leftBoundary.addToGame(this);
        rightBoundary.addToGame(this);
        bottomBoundary.addToGame(this);
        bottomBoundary.addHitListener(ballRemover);
        KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        Paddle paddle = new Paddle(new Point(350, 570), 100, 10);
        paddle.setKeyboard(keyboard);
        paddle.addToGame(this);
        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(100, 250, 5, Color.BLACK);
            ball.setVelocity(4 + i, 6 - i);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
        ballCounter.increase(3);
        // Create a pattern of blocks
        java.awt.Color[] rowColors = {
                java.awt.Color.RED,
                java.awt.Color.ORANGE,
                java.awt.Color.YELLOW,
                java.awt.Color.GREEN,
                java.awt.Color.BLUE,
                java.awt.Color.PINK
        };

        // Create multiple rows of blocks
        int blockWidth = 50;
        int blockHeight = 20;
        int startY = 150;

        for (int row = 0; row < rowColors.length; row++) {
            int numBlocks = 12 - row; // Decreasing blocks per row
            int startX = 780 - (blockWidth * numBlocks); // Right-align blocks

            for (int col = 0; col < numBlocks; col++) {
                Block block = new Block(
                        new Point(startX + (col * blockWidth), startY + (row * blockHeight)),
                        blockWidth,
                        blockHeight
                );
                block.setColor(rowColors[row]);
                block.addToGame(this);
                blockCounter.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(score);
            }
        }
    }

    /**
     * Starts the game loop where the animation is drawn, time is passed, and the game is continuously updated.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.blockCounter.getValue() > 0 && this.ballCounter.getValue() > 0) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.blockCounter.getValue() == 0) {
            this.score.getCounter().increase(100);
            System.out.println("You Win!\nYour score is: " + this.score.getValue());
        } else {
            System.out.println("Game Over.\nYour score is: " + this.score.getValue());
        }

        this.gui.close();
    }
    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the {@code Collidable} to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite object from the game's sprite collection.
     *
     * @param s the {@code Sprite} to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }


}