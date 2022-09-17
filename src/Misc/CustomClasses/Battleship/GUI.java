package Misc.CustomClasses.Battleship;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;

/**
 * @author Jacob Swineford
 */
public class GUI extends Application {

    private BattleGrid s1;
    private BattleGrid s2;
    private BattleGrid a1;
    private BattleGrid a2;
    private VBox shipyards;

    private static Color defaultColor = Color.LIGHTBLUE.brighter().brighter();
    private static Color selectionColor = Color.LIGHTGREEN.brighter().brighter();

    private Shipyard selected;
    private Battleship game;
    private int turn = 1; // 1 is player 1, 2 is player 2

    private static double SCENE_WIDTH = 1600;
    private static double SCENE_HEIGHT = 960;
    private static int gridWidth = 10;
    private static int gridHeight = 10;
    private static double cellSize = (SCENE_WIDTH / 3.5) / gridWidth;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.LIGHTGRAY);
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case R: {
                    if (selected != null) selected.ship.rotate();
                }
            }
        });
        placeGrids();
        placeShipyards();

        s1.setStatus("placement");
        s2.setStatus("placement");

        root.getChildren().addAll(s1, s2, a1, a2, shipyards);
        primaryStage.setTitle("Battleship");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void placeGrids() {
        double hm = 10;
        double vm = 10;
        game = new Battleship(gridWidth, gridHeight);

        s1 = new BattleGrid(gridHeight, gridWidth, cellSize, 1);
        s1.setTranslateY(SCENE_HEIGHT - s1.getBoundsInLocal().getHeight() - vm);
        s1.setTranslateX(hm);

        s2 = new BattleGrid(gridHeight, gridWidth, cellSize, 2);
        s2.setTranslateX(SCENE_WIDTH - s2.getBoundsInLocal().getWidth() - hm);
        s2.setTranslateY(SCENE_HEIGHT - s2.getBoundsInLocal().getHeight() - vm);

        a1 = new BattleGrid(gridHeight, gridWidth, cellSize, 1);
        a1.setTranslateX(hm);
        a1.setTranslateY(vm);

        a2 = new BattleGrid(gridHeight, gridWidth, cellSize, 2);
        a2.setTranslateX(SCENE_WIDTH - a2.getBoundsInLocal().getWidth() - hm);
        a2.setTranslateY(vm);
    }
    private void placeShipyards() {
        double cellSize = (SCENE_WIDTH / 3.5) / gridWidth;
        Ship carrier = new Ship("Carrier", 5, Color.DARKGREEN);
        Ship battleship = new Ship("Battleship", 4, Color.DARKBLUE);
        Ship destroyer = new Ship("Destroyer", 3, Color.ORANGE);
        Ship submarine = new Ship("Submarine", 3, Color.PURPLE);
        Ship patrolBoat = new Ship("Patrol Boat", 2, Color.PINK);
        HBox h1 = completeYard(carrier);
        HBox h2 = completeYard(battleship);
        HBox h3 = completeYard(destroyer);
        HBox h4 = completeYard(submarine);
        HBox h5 = completeYard(patrolBoat);
        shipyards = new VBox();
        shipyards.setSpacing(10);
        shipyards.setTranslateX(cellSize * gridWidth + 50);
        shipyards.setTranslateY(cellSize * gridHeight + 60);
        shipyards.getChildren().addAll(h1, h2, h3, h4, h5);
    }
    private HBox completeYard(Ship ship) {
        HBox com = new HBox();
        com.setSpacing(20);
        Shipyard s = new Shipyard(ship);
        Text t = new Text();
        com.getChildren().addAll(s, t);

        StringBuilder sb = new StringBuilder(ship.getName() + " - ");
        for (int i = 0; i < ship.getLength(); i++)
            sb.append('#');
        t.setText(sb.toString());
        t.setFont(Font.font(Font.getFamilies().get(1), 20));
        t.setTranslateY(.5 * s.getHeight() - .5 * t.getBoundsInLocal().getHeight());
        return com;
    }

    private Rectangle getBackground(Pane pane) {
        Node n = pane.getChildren().get(0);
        assert n instanceof Rectangle;
        return (Rectangle) n;
    }

    private class BattleGrid extends Pane {
        private int player;
        private Pane[][] grid;
        private String status;

        BattleGrid(int height, int width, double cellSize, int player) {
            grid = new Pane[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Pane cell = new Pane();
                    cell.setTranslateX(cellSize * j);
                    cell.setTranslateY(cellSize * i);
                    cell.setId(j+","+i);

                    Rectangle bg = new Rectangle();
                    bg.setWidth(cellSize);
                    bg.setHeight(cellSize);
                    bg.setStroke(Color.BLACK);
                    bg.setStrokeWidth(4);
                    bg.setFill(defaultColor);
                    cell.getChildren().add(bg);

                    grid[i][j] = cell;
                    this.getChildren().add(cell);
                }
            }
            status = "invisible";
            setMouseEvents();
        }

        void setStatus(String setTo) {status = setTo;}

        // used for placement
        private LinkedList<int[]> coor;

        private void setMouseEvents() {
            for (Pane[] arr : grid) {
                for (Pane cell : arr) {
                    cell.setOnMouseEntered(event -> {
                        switch (status) {
                            case "placement": {
                                if (selected != null)
                                    coor = game.canPlace(turn, selected.ship, getX(cell), getY(cell));

                                ((Rectangle) cell.getChildren().get(0)).setFill(selectionColor);
                                if (coor != null) {
                                    addShip(Color.TRANSPARENT, false);
                                }
                            }

                            case "attack": {

                            }
                        }
                    });

                    cell.setOnMouseExited(event -> {
                        switch (status) {
                            case "placement": {
                                ((Rectangle) cell.getChildren().get(0)).setFill(defaultColor);

                                if (coor != null) {
                                    for (int[] c : coor)
                                        grid[c[1]][c[0]].getChildren().remove(1);
                                    coor = null;
                                }
                            }

                            case "attack": {

                            }

                            // none for display and invisible
                        }
                    });

                    cell.setOnMouseClicked(event -> {
                        if (status.equals("placement")) {
                            addShip(selected.ship.getColor(), true);

                        } else if (status.equals("attack")) {


                        } else {} // invisible
                    });
                }
            }
        }

        private void addShip(Color color, boolean place) {
            if (coor != null) {
                for (int[] arr : coor)
                    addShipNode(grid[arr[1]][arr[0]], color);
                if (place) game.place(turn, selected, coor);
            }
        }
        private void addShipNode(Pane pane, Color color) {
            Rectangle r = new Rectangle();
            Rectangle bg = (Rectangle) pane.getChildren().get(0);
            r.setWidth(bg.getWidth() / 2.5);
            r.setHeight(bg.getHeight() / 2.5);
            r.setFill(color);
            r.setStroke(Color.BLACK);
            r.setStrokeWidth(3);
            r.setX(.5 * bg.getWidth() - .5 * r.getWidth());
            r.setY(.5 * bg.getHeight() - .5 * r.getHeight());
            pane.getChildren().add(r);
        }
        private int getX(Pane cell) {
            String x = cell.getId().substring(0, cell.getId().indexOf(","));
            return Integer.parseInt(x);
        }
        private int getY(Pane cell) {
            String y = cell.getId().substring(cell.getId().indexOf(",") + 1);
            return Integer.parseInt(y);
        }
    }

    class Shipyard extends Rectangle {

        // type of ship to produce
        private Ship ship;

        Shipyard(Ship ship) {
            super();
            this.ship = ship;
            this.setWidth(200);
            this.setHeight(70);
            this.setStroke(Color.BLACK);
            this.setStrokeWidth(3);
            this.setFill(ship.getColor());
            this.setOnMouseEntered(event -> {
                if (this.getStroke() != Color.DARKRED)
                    this.setStroke(Color.DARKGRAY);
            });
            this.setOnMouseExited(event -> {
                if (this.getStroke() != Color.DARKRED)
                    this.setStroke(Color.BLACK);
            });
            this.setOnMouseClicked(event -> {
                if (selected != this) {
                    if (selected != null) selected.setStroke(Color.BLACK);
                    selected = this;
                    this.setStroke(Color.DARKRED);
                } else {
                    selected = null;
                    this.setStroke(Color.DARKGRAY);
                }
            });
        }

        Ship produce() {
            Ship ship = new Ship(this.ship.getName(), this.ship.getLength(), this.ship.getColor());
            if (this.ship.isVertical()) ship.rotate();
            return ship;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
