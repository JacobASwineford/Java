package Misc.GUIS;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class GraphingThing extends Application {

    private Point2D origin;


    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final double SCENE_WIDTH = 1700;
        final double SCENE_HEIGHT = 900;
        final double DOT_DENSITY = 1.5;
        origin = new Point2D(SCENE_WIDTH / 2, SCENE_HEIGHT / 2);
        final double LEFT_BOUND = -500;
        final double RIGHT_BOUND = 500;
        Circle tester = new Circle(LEFT_BOUND + origin.getX(), origin.getY(), 5);
        Circle tester2 = new Circle(RIGHT_BOUND + origin.getX(), origin.getY(), 5);
        root.getChildren().add(tester);
        root.getChildren().add(tester2);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        Pane xa = create2DAxis(0, origin.getY(), 10000, origin.getY());
        Pane ya = create2DAxis(origin.getX(), 0, origin.getX(), 10000);
        root.getChildren().addAll(xa, ya);

        for (double x = LEFT_BOUND; x < RIGHT_BOUND; x+=DOT_DENSITY/20) {
            double y = 1 - (x/2);
            y=(-y);
            Circle dot = new Circle(x + origin.getX(), y + origin.getY(), DOT_DENSITY);
            dot.setFill(Color.BLUE);
            root.getChildren().add(dot);
        }

        primaryStage.setTitle("GraphingThing");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static Pane create2DAxis(double sx, double sy, double ex, double ey) {
        Pane p = new Pane();
        Line axisBase = new Line(sx, sy, ex, ey);
        p.getChildren().add(axisBase);
        // notch wip
        return p;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
