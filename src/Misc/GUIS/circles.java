package Misc.GUIS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jacob Swineford
 */
public class circles extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        root.setOnMouseClicked(event -> {
            // create a circle
            Circle c = new Circle();
            c.setRadius(100);
            c.setFill(new Color(1, 0, 0, 1));
            double xLo = event.getX();
            double yLo = event.getY();
            c.setCenterX(xLo);
            c.setCenterY(yLo);
            c.setStroke(new Color(0, 1, 0, 1));
            c.setStrokeWidth(10);
            root.getChildren().add(c);
        });

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public double pt(int x, int y) {
        // circle center dummy values
        double circleX = 100;
        double circleY = 100;

        double ptX = circleX - x;
        double ptY = circleY - y;
        double sum = Math.pow(ptX, 2) + Math.pow(ptY, 2);
        
        return Math.sqrt(sum);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
