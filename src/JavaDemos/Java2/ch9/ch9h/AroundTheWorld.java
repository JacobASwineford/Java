package JavaDemos.Java2.ch9.ch9h;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Transitions: a dot moves around a circular path. Clicking the mouse causes the
 * dot to reverse direction.
 * @author Jacob Swineford
 */
public class AroundTheWorld extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        final double SIZE = 800;
        Scene scene = new Scene(root, SIZE, SIZE, Color.BLACK);

        // create a circle that nearly fills the scene.
        Circle world = new Circle(SIZE / 2 - 10);
        world.setStroke(Color.DARKGREEN);
        world.setStrokeWidth(6);
        world.setFill(Color.DARKBLUE);

        // create a dot that travels around the world
        Circle dot = new Circle(6);
        dot.setFill(Color.PINK);

        // Path transition for dot traveling around the circle
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(500));
        pt.setPath(world);
        pt.setNode(dot);
        pt.setInterpolator(Interpolator.LINEAR);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(false);
        pt.play();

        world.setOnMouseClicked(event -> pt.setRate(-pt.getRate()));

        root.getChildren().addAll(world, dot);


        primaryStage.setTitle("Colorful grid");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

