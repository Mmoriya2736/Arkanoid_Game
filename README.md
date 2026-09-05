# Arkanoid Game (Java)

A dynamic 2D **Arkanoid / Brick Breaker** desktop game built from scratch in Java, utilizing Object-Oriented Programming (OOP) design patterns, custom 2D vector geometry, and an event-driven collision detection system.

---

## 🎮 Game Overview

The objective of the game is to clear rows of colored blocks using bouncing balls controlled via a player-driven paddle.

- **Screen Dimensions:** 800 × 600 pixels running at 60 FPS.
- **Starting Lives / Balls:** 3 simultaneous balls launched at varying trajectories.
- **Dynamic Paddle:** Divided into 5 distinct strike zones. Depending on where the ball strikes the paddle, it bounces at different angles (from 210° to 330°), giving the player strategic control over ball direction.
- **Color Changing Mechanics:** Balls change color to match the block they hit.
- **Score System:**
  - **+5 points** for each destroyed block.
  - **+100 bonus points** upon destroying all blocks and winning the game.
- **Win & Lose Conditions:**
  - **Victory:** Clear all blocks from the board.
  - **Game Over:** Lose all 3 balls past the bottom boundary.

---

## 🕹️ Controls

| Key | Action |
| :--- | :--- |
| `Left Arrow` ($\leftarrow$) | Move paddle left (wraps around screen boundaries) |
| `Right Arrow` ($\ightarrow$) | Move paddle right (wraps around screen boundaries) |

---

## 🏗️ Architecture & Design Patterns

The project emphasizes clean architecture, modularity, and established software design patterns:

1. **Observer Pattern (`HitListener` / `HitNotifier`):**
   - Decouples collision detection from game mechanics.
   - Blocks publish hit events to listeners:
     - `BlockRemover`: Removes the block from the game and decrements the block counter.
     - `BallRemover`: Catches balls entering the bottom death zone.
     - `ScoreTrackingListener`: Manages and increments player score.

2. **Composite / Collection Pattern (`Sprite` & `SpriteCollection`):**
   - All visual elements (`Ball`, `Block`, `Paddle`, `ScoreIndicator`) implement the `Sprite` interface (`drawOn()`, `timePassed()`).
   - The game loop iterates over `SpriteCollection` to render and tick all entities synchronously.

3. **Collision Detection System (`GameEnvironment` & `Collidable`):**
   - Implements custom 2D geometric algorithms (`Point`, `Line`, `Rectangle`).
   - Uses sub-stepping trajectory intersection to calculate exact collision points and prevent fast-moving balls from tunneling through obstacles.

4. **Animation & Timing Loop:**
   - Central game loop timed to maintain a smooth 60 FPS refresh rate using delta sleep timing.

---

## 📁 Project Structure

```text
.
├── biuoop-1.4.jar              # Game GUI and graphics library
├── build.xml                   # Apache Ant build configuration
├── src/
│   ├── Ass5Game.java           # Application entry point (main)
│   ├── Collision/              # Collision detection interfaces and math
│   │   ├── Collidable.java
│   │   ├── CollisionInfo.java
│   │   └── Velocity.java
│   ├── Game/                   # Core engine, listeners, and loop logic
│   │   ├── BallRemover.java
│   │   ├── BlockRemover.java
│   │   ├── Counter.java
│   │   ├── Game.java
│   │   ├── GameEnvironment.java
│   │   ├── HitListener.java
│   │   ├── HitNotifier.java
│   │   ├── ScoreTrackingListener.java
│   │   └── Tools.java
│   ├── Geometry/               # 2D geometry primitives
│   │   ├── Line.java
│   │   ├── Point.java
│   │   └── Rectangle.java
│   └── Sprite/                 # Visual elements and state update entities
│       ├── Ball.java
│       ├── Block.java
│       ├── Paddle.java
│       ├── ScoreIndicator.java
│       ├── Sprite.java
│       └── SpriteCollection.java
└── README.md
```

---

## 🚀 How to Run the Game

### Prerequisites
- **Java Development Kit (JDK) 11 or higher** installed.
- Optional: **Apache Ant** (for CLI build automation).

---

### Option 1: Using Apache Ant (Recommended)

From the project root directory:

1. **Compile and Run directly:**
   ```bash
   ant run
   ```

2. **Clean build artifacts:**
   ```bash
   ant clean
   ```

---

### Option 2: Using the Command Line (Terminal / CMD / PowerShell)

1. **Compile the source code into the `bin` directory:**

   * **Linux / macOS:**
     ```bash
     mkdir -p bin
     javac -cp biuoop-1.4.jar -d bin src/*.java src/*/*.java
     ```

   * **Windows (Command Prompt / PowerShell):**
     ```cmd
     if not exist bin mkdir bin
     javac -cp "biuoop-1.4.jar" -d bin src/*.java src/*/*.java
     ```

2. **Run the game:**

   * **Linux / macOS:**
     ```bash
     java -cp bin:biuoop-1.4.jar Ass5Game
     ```

   * **Windows:**
     ```cmd
     java -cp "bin;biuoop-1.4.jar" Ass5Game
     ```

---

### Option 3: Using an IDE (IntelliJ IDEA / Eclipse)

1. Open the project folder in your IDE.
2. Ensure the JDK (version 11+) is configured as the Project SDK.
3. Add `biuoop-1.4.jar` as a project library dependency:
   - **IntelliJ IDEA:** Go to `File` $
ightarrow$ `Project Structure` $
ightarrow$ `Libraries` $
ightarrow$ `+` (Add Java) $
ightarrow$ Select `biuoop-1.4.jar`.
   - **Eclipse:** Right-click the project $
ightarrow$ `Build Path` $
ightarrow$ `Add External Archives` $
ightarrow$ Select `biuoop-1.4.jar`.
4. Locate `src/Ass5Game.java`.
5. Right-click on `Ass5Game.java` and select **Run 'Ass5Game.main()'**.

---

## 👩‍💻 Author

- **Moriya Malkiel**
