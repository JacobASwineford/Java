package Misc.CustomClasses.Checkers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class CheckersGUI extends Application {

    int length = 2;

    @Override
    public void start(Stage primaryStage)  {

        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 1000;
        length+=2;
        try {
            Pane root = new Pane();
            Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
            Checkers c = new Checkers(scene, 10);
            root.getChildren().add(c.getMaster());
            primaryStage.setTitle("Checkers");
            primaryStage.setAlwaysOnTop(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
