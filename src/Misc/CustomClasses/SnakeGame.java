package Misc.CustomClasses;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends Application {
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 800;
    private static final int UNIT_SIZE = 40;
    private static final int GAME_WIDTH = WIDTH / UNIT_SIZE;
    private static final int GAME_HEIGHT = HEIGHT / UNIT_SIZE;

    private List<Point> snake;
    private Point food;
    private Direction direction;
    private boolean gameOver;

    private Random random;

    public SnakeGame() {
        snake = new ArrayList<>();
        random = new Random();
        direction = Direction.RIGHT;
        gameOver = false;
        createFood();
        snake.add(new Point(GAME_WIDTH / 2, GAME_HEIGHT / 2));
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP && direction != Direction.DOWN) {
                direction = Direction.UP;
            } else if (e.getCode() == KeyCode.DOWN && direction != Direction.UP) {
                direction = Direction.DOWN;
            } else if (e.getCode() == KeyCode.LEFT && direction != Direction.RIGHT) {
                direction = Direction.LEFT;
            } else if (e.getCode() == KeyCode.RIGHT && direction != Direction.LEFT) {
                direction = Direction.RIGHT;
            }
        });

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            while (!gameOver) {
                update();
                draw(gc);
                Platform.runLater(() -> {

                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void update() {
        if (gameOver) {
            return;
        }

        Point head = snake.get(0);
        Point newHead;

        switch (direction) {
            case UP:
                newHead = new Point(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                newHead = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                newHead = new Point(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                newHead = new Point(head.getX() + 1, head.getY());
                break;
            default:
                throw new IllegalArgumentException("Invalid direction.");
        }

        if (newHead.getX() < 0 || newHead.getX() >= GAME_WIDTH ||
                newHead.getY() < 0 || newHead.getY() >= GAME_HEIGHT ||
                snake.contains(newHead)) {
            gameOver = true;
            return;
        }

        snake.add(0, newHead);

        if (newHead.equals(food)) {
            createFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        if (gameOver) {
            gc.setFill(Color.RED);
            gc.fillText("Game Over", WIDTH / 2 - 50, HEIGHT / 2);
            return;
        }

        gc.setFill(Color.GREEN);
        for (Point point : snake) {
            gc.fillRect(point.getX() * UNIT_SIZE, point.getY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }

        gc.setFill(Color.RED);
        gc.fillRect(food.getX() * UNIT_SIZE, food.getY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
    }

    private void createFood() {
        int x = random.nextInt(GAME_WIDTH);
        int y = random.nextInt(GAME_HEIGHT);

        food = new Point(x, y);
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return 123213;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

