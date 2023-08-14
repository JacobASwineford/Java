package Misc.CustomClasses.TowerDefense;

import Misc.CustomClasses.TowerDefense.Tiles.OtherTiles.SpawnTile;
import Misc.CustomClasses.TowerDefense.Tiles.PlacementTiles.GrassTile;
import Misc.CustomClasses.TowerDefense.Tiles.OtherTiles.RemovalTile;
import Misc.CustomClasses.TowerDefense.Tiles.Tile;
import Misc.CustomClasses.TowerDefense.Tiles.OtherTiles.TowerSelectionTile;
import Misc.CustomClasses.TowerDefense.Tiles.TileGrid;
import Misc.CustomClasses.TowerDefense.Towers.Projectiles.Bullet;
import Misc.CustomClasses.TowerDefense.Towers.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Tower ATTACHED_TOWER = null;
    public static Pane ATTACHED = null;
    public static double ATTACHED_WIDTH;
    public static double ATTACHED_HEIGHT;

    public static double MOUSE_X;
    public static double MOUSE_Y;

    // start out in placement mode
    public static boolean PLACEMENT_MODE = true;
    public static boolean REMOVAL_MODE = false;

    public static Pane ROOT = new Pane();

    // list of bullets
    public static LinkedList<Bullet> BULLETS = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) {
        int SCENE_HEIGHT = 800;
        int SCENE_WIDTH = 1300;
        Scene scene = new Scene(ROOT, SCENE_WIDTH, SCENE_HEIGHT);

        int tileWidth = 100;
        int tileHeight = 100;

        // tower selection tiles
        TowerSelectionTile t = new TowerSelectionTile(tileWidth, tileHeight, "Shooter");
        TowerSelectionTile t2 = new TowerSelectionTile(tileWidth, tileHeight, "Double Shooter");
        TowerSelectionTile t3 = new TowerSelectionTile(tileWidth, tileHeight, "Defender");

        // removal tile
        RemovalTile rt = new RemovalTile(tileWidth, tileHeight);

        // tile grid for tile selection tiles
        TileGrid g = new TileGrid(4, new Tile[] {t, t2, t3, rt});
        g.setLayoutX((SCENE_WIDTH - g.getGridWidth()) / 2.0);
        g.setLayoutY(30);
        ROOT.getChildren().add(g);

        // field tiles
        int grassPerCol = 8;
        int fieldCol = 9;
        int fieldRows = 5;
        Tile[] fieldTiles = new Tile[fieldCol * fieldRows];

        int c = 0;
        for (int i = 0; i < fieldRows; i++) {
            for (int j = 0; j < grassPerCol; j++)
                fieldTiles[c++] = new GrassTile(tileWidth, tileHeight);
            fieldTiles[c++] = new SpawnTile(tileWidth, tileHeight);
        }

        // tile grid for field tiles
        TileGrid f = new TileGrid(fieldCol, fieldTiles);
        f.setLayoutX((SCENE_WIDTH - f.getGridWidth()) / 2.0);
        f.setLayoutY((SCENE_HEIGHT - f.getGridHeight()) / 2.0);
        ROOT.getChildren().add(f);

        // set a thread to track the mouse
        scene.setOnMouseMoved(mouseEvent -> {
            MOUSE_X = mouseEvent.getX();
            MOUSE_Y = mouseEvent.getY();
        });

        // set a Keyframe to track shadow panes following the mouse
        KeyFrame kf = new KeyFrame(Duration.millis(1), actionEvent -> {
            if (ATTACHED != null) {
                ATTACHED.setLayoutX(MOUSE_X - ATTACHED_WIDTH / 2);
                ATTACHED.setLayoutY(MOUSE_Y - ATTACHED_HEIGHT / 2);
            }
        });
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // set mouse presses for the scene
        scene.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY &&
            ATTACHED_TOWER != null) {
                ROOT.getChildren().remove(ATTACHED);
                ATTACHED_TOWER = null;
            }
        });

        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Tower Defense!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Switches the game to removal mode.
     */
    public static void toRemovalMode() {
        if (ATTACHED_TOWER != null) {
            ROOT.getChildren().remove(ATTACHED);
            ATTACHED_TOWER = null;
        }

        // all other modes false
        PLACEMENT_MODE = false;

        // removal mode true
        REMOVAL_MODE = true;
    }

    /**
     * Switches the game to placement mode.
     */
    public static void toPlacementMode() {
        // all other modes false
        REMOVAL_MODE = false;

        // placement mode true
        PLACEMENT_MODE = true;
    }
}
