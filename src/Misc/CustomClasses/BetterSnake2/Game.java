package Misc.CustomClasses.BetterSnake2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.concurrent.ThreadLocalRandom;

/**
 * GUI for the game of Snake. Snake is a game where a snake is controlled
 * moving through a field eating fruit. Once a fruit is eaten, the snake grows by
 * one segment. collision with either the border of the field will result in a
 * game over.
 *
 * @author Jacob Swineford
 */
public class Game extends Application {

    private Rectangle[][] field;
    private Snake snake;

    private static Color SNAKE_COLOR = Color.BLUE;
    private static Color FRUIT_COLOR = Color.PINK;
    private static Color SQUARE_COLOR = Color.WHITE;

    private boolean paused = false;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final double SCENE_WIDTH = 800;
        final double SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        int width = 70;
        int height = 70;
        double sh = SCENE_HEIGHT / height;
        double sw = SCENE_WIDTH / width;

        // generate field
        field = new Rectangle[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Rectangle square = new Rectangle(sw, sh);
                square.setFill(SQUARE_COLOR);
                square.setX(j * sw);
                square.setY(i * sh);
                field[i][j] = square;
                root.getChildren().add(square);
            }
        }

        // add snake
        snake = new Snake(10, 10, 5);
        for (Segment s : snake.getSegments())
            field[s.getY()][s.getX()].setFill(SNAKE_COLOR);

        // generate fruit
        generateFruit(50);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE: start(); break;
                case P: pause(); break;
                case R: reset(); break;
                case W: snake.setDirection(new int[] {0, -1}); break;
                case A: snake.setDirection(new int[] {-1, 0}); break;
                case D: snake.setDirection(new int[] {1, 0}); break;
                case S: snake.setDirection(new int[] {0, 1});
            }
        });

        primaryStage.setTitle("Snake");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Updates the board with everything's position. If the head of the snake
     * is currently occupying a space with a fruit, then expand the snake.
     */
    private void update() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (headMatch(j, i) && field[i][j].getFill() == FRUIT_COLOR) {
                    snake.expand();
                    field[i][j].setFill(SNAKE_COLOR);
                    generateFruit(1);
                } else if (segmentMatch(j, i)) field[i][j].setFill(SNAKE_COLOR);
                else if (field[i][j].getFill() != FRUIT_COLOR)
                    field[i][j].setFill(SQUARE_COLOR);

            }
        }
    }

    /**
     * Generates a set amount of fruit on the field.
     *
     * @param amount given amount to generate
     */
    private void generateFruit(int amount) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (amount != 0) {
            int x = rand.nextInt(field[0].length);
            int y = rand.nextInt(field.length);
            if (field[y][x].getFill() != FRUIT_COLOR &&
                    field[y][x].getFill() != SNAKE_COLOR) {
                field[y][x].setFill(FRUIT_COLOR);
                amount--;
            }
        }
    }

    /**
     * Checks to see if the list of segments for the snake
     * contains a segment that matches with the given coordinates.
     *
     * @return true if so, false otherwise
     */
    private boolean segmentMatch(int x, int y) {
        for (Segment s : snake.getSegments()) {
            if (s.getX() == x && s.getY() == y) return true;
        }
        return false;
    }

    /**
     * Checks to see if the head of the snake is currently occupying a
     * given set of coordinates.
     *
     * @param x given x
     * @param y given y
     * @return true if so, false otherwise
     */
    private boolean headMatch(int x, int y) {
        Segment head = snake.getSegments().getFirst();
        return head.getX() == x && head.getY() == y;
    }

    class SnakeEngine extends Thread {

        int tickMillis = 50;

        @Override
        public void run() {
            while (true) {
                try {
                    if (paused) continue;
                    Thread.sleep(tickMillis);
                    snake.advance();
                    update();

                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    /**
     * Starts the game of snake.
     */
    private void start() {
        new SnakeEngine().start();
    }

    /**
     * Pauses and un-pauses the game of snake.
     */
    private void pause() {
        if (paused) {
            paused = false;
            return;
        }
        paused = true;
    }

    private void reset() {

    }

    public static void main(String[] args) {
        launch(args);
    }


}
