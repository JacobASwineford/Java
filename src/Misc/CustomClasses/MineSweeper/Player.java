package Misc.CustomClasses.MineSweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Invokes the Minesweeper engine to play Minesweeper.
 *
 * @author Jacob Swineford
 */
public class Player extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 900;
        final int SCENE_HEIGHT = 900;
        final int rows = 20;
        final int cols = 20;
        final int w = SCENE_WIDTH / cols;
        final int h = SCENE_HEIGHT / rows;
        final int mines = 100;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        Minesweeper m = new Minesweeper(rows, cols, mines, w, h);
        root.getChildren().add(m.getGrid());

        primaryStage.setTitle("Minesweeper");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
