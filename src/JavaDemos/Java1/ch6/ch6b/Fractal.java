package JavaDemos.Java1.ch6.ch6b;

/**
 * @author Jacob Swineford
 */

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;

public class Fractal extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int N = 500;
        Scene scene = new Scene(root, N, N, Color.SLATEGRAY);

        // Three corners of an equilateral triangle
        final int GAP = 10;
        Point2D p1 = new Point2D(N / 2, GAP);
        Point2D p2 = new Point2D(GAP, N - GAP);
        Point2D p3 = new Point2D(N - GAP, N - GAP);

        final int NUM_DOTS = 100_000;

        // 1. pick a corner at random (current point).
        // 2. Pick a corner at random (next point).
        // 3. Set current to the point midway between current and the next.
        // 4. Draw current point and go back to step 2.
        Point2D current = getPoint(p1, p2, p3);
        for (int i = 0; i < NUM_DOTS; i++) {
            Point2D next = getPoint(p1, p2, p3);
            current = midpoint(current, next);

            // draw a tiny circle at the current location
            double x = current.getX();
            double y = current.getY();
            Circle dot = new Circle(x, y, 1);
            dot.setFill(Color.PINK);
            dot.setStroke(Color.BLACK);
            dot.setStrokeWidth(.1);
            root.getChildren().add(dot);
        }

        primaryStage.setTitle("Empty Scene");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    /**
     * Returns the midpoint of the line segment joining two given points.
     */
    private static Point2D midpoint(Point2D p1,Point2D p2) {
        double a = (p1.getX() + p2.getX()) / 2;
        double b = (p1.getY() + p2.getY()) / 2;
        return new Point2D(a, b);
    }

    /**
     * Returns one of three given points at random.
     */
    private static Point2D getPoint( Point2D a, Point2D b, Point2D c) {
        Random rand = new Random();
        int k = rand.nextInt(3);
        switch (rand.nextInt(3)) {
            case 0: return a;
            case 1: return b;
            case 2: return c;
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);

    }


}

