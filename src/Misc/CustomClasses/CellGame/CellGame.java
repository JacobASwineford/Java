package Misc.CustomClasses.CellGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A little simulation for conways game of life. This simulation
 * can be used to simply represent underpopulation and overpopulation.
 * Here are the rules:
 * \n
 *  1. Any live cell with fewer than two neighbors dies.
 *  2. Any live cell with two or three neighbors lives.
 *  3. Any live cell with more than three neighbors dies.
 *  3. Any dead cell with exactly three neighbors becomes a live
 *     cell.
 */

public class CellGame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final int SCENE_HEIGHT = 800;
    private final int SCENE_WIDTH = 1600;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        root.getChildren().add(new ConwayGrid(100, 200, scene));

        primaryStage.setTitle("Conway's game of life!");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ConwayGrid extends Pane {

        /**
         * false is a dead cell, true is a live cell.
         */
        private final boolean[][] cells;
        private final boolean[][] next;
        private final Rectangle[][] rectangles;

        private final int rows;
        private final int columns;

        private final Color DEAD_COLOR = Color.WHITESMOKE;
        private final Color LIVE_COLOR = Color.GREY;

        ConwayGrid(int rows, int columns, Scene scene) {
            this.rows = rows;
            this.columns = columns;
            cells = new boolean[rows][columns];
            next = new boolean[rows][columns];
            rectangles = new Rectangle[rows][columns];
            int strokeWidth = 0;
            int width = SCENE_WIDTH / columns;
            int height = SCENE_HEIGHT / rows;
            int x = 0, y = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Rectangle r = new Rectangle();
                    r.setWidth(width);
                    r.setHeight(height);
                    r.setStroke(Color.BLACK);
                    r.setStrokeWidth(strokeWidth);
                    r.setFill(DEAD_COLOR);

                    r.setOnMouseDragged(event -> {
                        int xc = (int) event.getX() / width;
                        int yc = (int) event.getY() / height;
                        cells[yc][xc] = true;
                        rectangles[yc][xc].setFill(LIVE_COLOR);
                    });

                    r.setX(x);
                    r.setY(y);
                    x += width;
                    rectangles[i][j] = r;
                    this.getChildren().add(r);
                }
                x = 0;
                y += height;
            }

            initCells(scene);
        }

        /**
         * This method is called by the constructor to initialize
         * live cells.
         */
        private void initCells(Scene scene) {

            KeyFrame kf = new KeyFrame(Duration.millis(20), event ->
            {
                tick();
                update();
            });
            Timeline t = new Timeline(kf);
            t.setCycleCount(Timeline.INDEFINITE);
            t.play();

            ThreadLocalRandom rand = ThreadLocalRandom.current();
            KeyFrame kf2 = new KeyFrame(Duration.millis(500), event -> {
                for (int i = 0; i < 10; i++)
                    LWSS(rand.nextInt(columns - 10), rand.nextInt(rows - 10));
            });
            Timeline t2 = new Timeline(kf2);
            t2.setCycleCount(Timeline.INDEFINITE);
            t2.play();

            scene.setOnKeyPressed(keyEvent -> {
                KeyCode code = keyEvent.getCode();
                switch (code) {
                    case P:
                        t.play();
                        break;
                    case S:
                        t.stop();
                        break;
                }
            });
        }

        /**
         * Sets a blinker in the specified location.
         *
         * @param x x coordinate
         * @param y y coordinate
         */
        private void blinker(int x, int y) {
            int[][] coors = {
                    {x + 1, y},
                    {x + 1, y + 1},
                    {x + 1, y + 2}
            };

            for (int[] coor : coors) {
                x = coor[0];
                y = coor[1];
                rectangles[y][x].setFill(LIVE_COLOR);
                cells[y][x] = true;
            }
        }

        /**
         * Sets a glider in a specified location.
         *
         * @param x x coordinate
         * @param y y coordinate
         */
        private void glider(int x, int y) {
            int[][] coors = {
                    {x, y + 1},
                    {x + 1, y + 2},
                    {x + 2, y + 2},
                    {x + 2, y + 1},
                    {x + 2, y}
            };

            for (int[] coor : coors) {
                x = coor[0];
                y = coor[1];
                rectangles[y][x].setFill(LIVE_COLOR);
                cells[y][x] = true;
            }
        }

        /**
         * Sets a Lightweight spaceship in a specific location.
         *
         * @param x x coordinate
         * @param y y coordinate
         */
        private void LWSS(int x, int y) {
            int[][] coors = {
                    {x + 3, y },
                    {x + 4, y + 1},
                    {x + 4, y + 2},
                    {x + 4, y + 3},
                    {x + 3, y + 3},
                    {x + 2, y + 3},
                    {x + 1, y + 3},
                    {x, y + 2},
                    {x, y}
            };

            for (int[] coor : coors) {
                x = coor[0];
                y = coor[1];
                rectangles[y][x].setFill(LIVE_COLOR);
                cells[y][x] = true;
            }
        }

        /**
         * Ticks the live and dead cells of the grid to the next generation.
         */
        private void tick() {
            int n;

            next[0][0] = true;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    n = neighbors(j, i);
                    if (cells[i][j] && (n < 2 || n > 3)) { // live cell to dead
                        next[i][j] = false;
                    } else if (n == 3) { // dead cell to live
                        next[i][j] = true;
                    } else
                        next[i][j] = cells[i][j];
                }
            }
        }

        /**
         * updates the grid to the latest generation.
         */
        private void update() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    cells[i][j] = next[i][j];
                    if (cells[i][j])
                        rectangles[i][j].setFill(LIVE_COLOR);
                    else
                        rectangles[i][j].setFill(DEAD_COLOR);
                }
            }
        }

        /**
         * Returns the number of live neighbors a cell has. More specifically,
         * This method targets the neighbors of the current generation.
         *
         * @param x x coordinate
         * @param y y coordinate
         * @return number of live neighbors
         */
        private int neighbors(int x, int y) {
            int[][] ne = {
                    {x, y + 1},
                    {x, y - 1},
                    {x + 1, y + 1},
                    {x + 1, y - 1},
                    {x + 1, y},
                    {x - 1, y + 1},
                    {x - 1, y - 1},
                    {x - 1, y}
            };
            int n = 0;
            for (int[] coor : ne) {
                x = coor[0];
                y = coor[1];

                if (y < 0)
                    y += rows;
                else if (y >= rows)
                    y -= rows;

                if (x < 0)
                    x += columns;
                else if (x >= columns)
                    x -= columns;

                if (cells[y][x])
                    n++;
            }
            return n;
        }

    }
}
