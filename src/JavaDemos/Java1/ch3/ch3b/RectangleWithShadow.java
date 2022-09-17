package JavaDemos.Java1.ch3.ch3b;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Draws a randomly colored rectangle with rounded corners and a drop shadow.
 *
 * @author Jacob Swineford
 */
public class RectangleWithShadow extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        final int SCENE_WIDTH = 500;
        final int SCENE_HEIGHT = 300;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        // Create a random opaque color for the rectangle.
        Random rand = new Random();
        double r = rand.nextDouble();
        double g = rand.nextDouble();
        double b = rand.nextDouble();
        Color color = new Color(r, g, b, 1); //the three values go in the order red, blue, amd green.

        // Create a rectangle centered in the scene.
        final int RECT_WIDTH = 400;
        final int RECT_HEIGHT = 200;
        final int x = (SCENE_WIDTH - RECT_WIDTH) / 2; // x-coordinate of top left corner, p
        final int y = (SCENE_HEIGHT - RECT_HEIGHT) / 2; // y-coordinate of top left corner
        Rectangle rect = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT); //Rectangle must be within the .jx
        rect.setFill(color);

        //Round the corners.
        rect.setArcWidth(50);
        rect.setArcHeight(50);

        // Add a drop shadow
        DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetX(5.0);
        dropshadow.setOffsetY(5.0);
        dropshadow.setColor(Color.GRAY);
        rect.setEffect(dropshadow);

        root.getChildren().add(rect);
        primaryStage.setTitle("Rectangle with Shadow");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
