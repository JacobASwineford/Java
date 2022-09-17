package JavaDemos.Java1.ch4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import java.util.Random;

/**
 * @author Jacob Swineford
 */

public class RandomOvals extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane(); //Stack Pane centers all of it children
        final int SCENE_WIDTH = 500; // Local variable to the main class
        final int SCENE_HEIGHT = 450; //Local variable to the main class
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.PINK);

        final int MIN_SIZE = 50;
        final int MAX_SIZE = Math.min(SCENE_HEIGHT, SCENE_WIDTH) / 2 - 10;
        root.getChildren().add(getOval(MIN_SIZE, MAX_SIZE));
        root.getChildren().add(getOval(MIN_SIZE, MAX_SIZE));
        root.getChildren().add(getOval(MIN_SIZE, MAX_SIZE));
        root.getChildren().add(getOval(MIN_SIZE, MAX_SIZE));
        root.getChildren().add(getOval(MIN_SIZE, MAX_SIZE));
        root.getChildren().add(getOval(MIN_SIZE, MAX_SIZE));

        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Random Ovals");
        primaryStage.show();

    }

    /**
     * Returns an ellipse with randomly generated width and height within the given
     * bounds, with a randomly generated boundary color, and rotated through a
     * random angle.
     */

    private Ellipse getOval(int minradius, int maxradius) { //minimum radius and maximum radius
        Random rand = new Random();

        // create a random color
        double r = rand.nextDouble();
        double g = rand.nextDouble();
        double b = rand.nextDouble();
        Color color = new Color(r, g, b, 1);

        int radiusX = minradius + rand.nextInt(maxradius - minradius + 1);
        int radiusY = minradius + rand.nextInt(maxradius - minradius + 1);


        Ellipse e = new Ellipse(radiusX, radiusY);
        e.setStroke(color);
        e.setStrokeWidth(5);
        e.setFill(null); //nul
        e.setRotate(rand.nextInt(360));

        return e;
    }


    public static void main(String[] args) {
        launch(args);

    }
}

