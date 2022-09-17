package Misc.GUIS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class HitBoxes extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        Rectangle h1 = new Rectangle(100, 100);
        h1.setFill(Color.GREEN);
        h1.setStroke(Color.BLACK);
        h1.setStrokeWidth(4);
        h1.setX(150);
        h1.setY(150);

        h1.setOnMouseDragged(event -> {
            h1.setX(event.getX() - 50);
            h1.setY(event.getY() - 50);
        });

        Rectangle h2 = new Rectangle(100, 100);
        h2.setFill(Color.GREEN);
        h2.setStroke(Color.BLACK);
        h2.setStrokeWidth(4);
        h2.setX(400);
        h2.setY(400);

        h2.setOnMouseDragged(event -> {
            h2.setX(event.getX() - 50);
            h2.setY(event.getY() - 50);
        });

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case P: printPositions(h1, h2);
            }
        });

        class HitBoxThread extends Thread {

            @Override
            public void run() {
                while (true) {
                    if (intersecting2(h1, h2)) {
                        h1.setFill(Color.RED);
                        h2.setFill(Color.RED);
                    } else {
                        h1.setFill(Color.GREEN);
                        h2.setFill(Color.GREEN);
                    }
                }
            }
        }

        Thread hbt = new HitBoxThread();
        hbt.start();

        root.getChildren().addAll(h1, h2);

        primaryStage.setTitle("Hitboxes");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static boolean intersecting(Rectangle r1, Rectangle r2) {
        double dx = Math.abs(r1.getX() - r2.getX());
        double dy = Math.abs(r1.getY() - r2.getY());
        return dx <= r1.getWidth() && dy <= r1.getHeight();
    }

    private static boolean intersecting2(Rectangle r1, Rectangle r2) {
        return contained(r1.getX(), r1.getX() + r1.getWidth(), r2.getX(), r2.getX() + r2.getWidth()) &&
                contained(r1.getY(), r1.getY() + r1.getHeight(), r2.getY(), r2.getY() + r2.getHeight());
    }

    private static boolean contained(double b1, double b2, double b3, double b4) {
        return (b3 >= b1 && b3 <= b2) || (b4 >= b1 && b4 <= b2);
    }

    private static void printPositions(Rectangle r1, Rectangle r2) {
        System.out.println("R1: X -> " + r1.getX() + " X FAR -> " + (r1.getX() + r1.getWidth()));
        System.out.println("R1: Y -> " + r1.getY() + " Y FAR -> " + (r1.getY() + r1.getHeight()));
        System.out.println("R2: X -> " + r2.getX() + " X FAR -> " + (r2.getX() + r2.getWidth()));
        System.out.println("R2: Y -> " + r2.getY() + " Y FAR -> " + (r2.getY() + r2.getHeight()));
    }

    public static void main(String[] args) {
        launch(args);
    }


}
