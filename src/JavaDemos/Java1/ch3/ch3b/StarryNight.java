package JavaDemos.Java1.ch3.ch3b;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

/**
 *
 *
 * @author Jacob Swineford
 */

public class StarryNight extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        final int SCENE_WIDTH = 250;
        final int SCENE_HEIGHT = 500;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.BLACK);

        Color cl = new Color(0.2, 0.9, 0.1, .3);
        Color cl2 = cl.darker();
        Color cl3 = cl2.darker();

        // The star will be rendered as two triangles.
        final int CENTER_X = SCENE_WIDTH / 2; // x-coordinate of the center of the scene
        final int CENTER_Y = SCENE_HEIGHT / 2; // y-coordinate of the center of the scene
        final int RADIUS = 100; // radius of the circle that circumscribes each triangle

        // Vertices of first triangle are 90 degrees. 210 degrees, and 330 degrees around the center.
        double x1 = CENTER_X + RADIUS * Math.cos(Math.toRadians(90));
        double y1 = CENTER_Y - RADIUS * Math.sin(Math.toRadians(90));
        double x2 = CENTER_X + RADIUS * Math.cos(Math.toRadians(210));
        double y2 = CENTER_Y - RADIUS * Math.sin(Math.toRadians(210));
        double x3 = CENTER_X + RADIUS * Math.cos(Math.toRadians(330));
        double y3 = CENTER_Y - RADIUS * Math.sin(Math.toRadians(330));
        Polygon triangle = new Polygon(x1, y1, x2, y2, x3, y3);
        triangle.setFill(cl);

        // The Second triangle is initialized to the same state as the first and
        // then rotated and shifted so that the two triangles form a 6-pointed star.
        Polygon triangle2 = new Polygon(x1, y1, x2, y2, x3, y3);
        triangle2.setStroke(Color.GREEN); //highlights the selected star
        triangle2.setFill(cl2);
        triangle2.setTranslateY(RADIUS / 2); //t
        triangle2.setRotate(180); //rotates the triangle2 star 180 degrees from its original position

        Shape star = Shape.union(triangle, triangle2); //meshing the two shape together to form one shape
        star.setFill(Color.BLACK);
        star.setRotate(30);

        root.getChildren().addAll(triangle, triangle2, star);


        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true); //makes the primary stage stay on top of other windows
        primaryStage.setTitle("Starry Night");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}

