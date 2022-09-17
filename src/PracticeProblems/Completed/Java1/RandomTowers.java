package PracticeProblems.Completed.Java1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

/**
 * @author Jacob Swineford
 */
public class RandomTowers extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 1000;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.BLACK);

        int yCoor = 0;
        int height = 40;
        final int numOfRectangles = SCENE_HEIGHT / 40;

        for (int i = 0; i < numOfRectangles; i++) {
            Random rand = new Random();
            int xCoor = rand.nextInt(SCENE_WIDTH / 2);
            int width = ((SCENE_WIDTH / 2) - xCoor) * 2;

            Rectangle bar = new Rectangle(xCoor, yCoor, width, height);
            bar.setFill(Color.TRANSPARENT);
            bar.setStroke(Color.PINK);
            bar.setStrokeWidth(5);

            root.getChildren().add(bar);

            yCoor += 40;
        }
        primaryStage.setTitle("Random Bars");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
