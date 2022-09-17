package PracticeProblems.NotCompleted.Java1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 *
 *
 * only looks right for the current width and height
 * of the scene.
 * @author Jacob Swineford
 */
public class AroundTheSun extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        root.getChildren().add(getOrbits());

        primaryStage.setTitle("Orbital System");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * Creates the sun and the gray paths that represent
     * the planet's orbits.
     */
    private static Shape getOrbits() {

        Circle sun = new Circle((SCENE_HEIGHT / 2),  100, 100);

                return sun;
    }

    /**
     * Takes an arguement in kilometers and scales the size
     * of a planet to fit the scene
     */


    private static final double SCENE_WIDTH = 650;
    private static final double SCENE_HEIGHT = 650;

    public static void main(String[] args) {
        launch(args);
    }


}
