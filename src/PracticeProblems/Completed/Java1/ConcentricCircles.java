package PracticeProblems.Completed.Java1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Scanner;

/**
 * generates a defined number of circles centered in the middle
 * that are eqi-distant form one another. The circles are also
 * generated with a random color.
 *
 * @author Jacob Swineford
 */
public class ConcentricCircles extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter amount of circles: ");
        final int numCircles = in.nextInt();

        StackPane root = new StackPane();
        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 1000;
        Scene scene = new Scene(root, SCENE_WIDTH,
                SCENE_HEIGHT, Color.BLACK);

        primaryStage.setTitle("Concentric Circles");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

        int d = 10;
        double radiusX = ((double) SCENE_WIDTH / 2) - d;
        double radiusY = ((double) SCENE_HEIGHT / 2) - d;

        for (int i = 0; i < numCircles; i++) {
            Ellipse ellipse = getRandColorCircle(radiusX, radiusY);
            root.getChildren().add(ellipse);

            // refers to biggest circle for even-spaced proceeding circles
            radiusX -= (double) ((SCENE_WIDTH / 2) - d) / numCircles;
            radiusY -= (double) ((SCENE_HEIGHT / 2) - d) / numCircles;
        }
    }

    /**
     * Generates an ellipse with a random color.
     */
    private static Ellipse getRandColorCircle(double radiusX, double radiusY) {
        Random rand = new Random();

        int x = rand.nextInt(255) + 1;
        int y = rand.nextInt(255) + 1;
        int z = rand.nextInt(255) + 1;
        Color randomColor = Color.rgb(x, y, z, 1);

        Ellipse random = new Ellipse(radiusX, radiusY);
        random.setFill(randomColor);

        return random;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
