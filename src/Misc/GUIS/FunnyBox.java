package Misc.GUIS;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jacob Swineford
 */
public class FunnyBox extends Application {

    private double x = 0;
    private double y = 0;

    private int numClicks = 0;

    private LinkedList<PathTransition> transitions = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        for (int i = 0; i < 700; i++) {
            double x = rand.nextDouble(SCENE_WIDTH);
            double y = rand.nextDouble(SCENE_HEIGHT);
            double x2 = rand.nextDouble(SCENE_WIDTH);
            double y2 = rand.nextDouble(SCENE_HEIGHT);
            Line line = new Line(x, y, x2, y2);
            line.setStroke(randomColor());
            Rectangle r = new Rectangle(30, 30, randomColor());
            r.setX(x);
            r.setY(y);
            root.getChildren().addAll(line, r);

            PathTransition path = new PathTransition(Duration.seconds(1), line, r);
            path.setCycleCount(PathTransition.INDEFINITE);
            path.setAutoReverse(true);
            transitions.add(path);
        }

        scene.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY: {
                    if (numClicks % 2 == 0) {
                        x = event.getX();
                        y = event.getY();
                    } else {
                        double x2 = event.getX();
                        double y2 = event.getY();
                        Line line = new Line(x, y, x2, y2);
                        line.setStroke(randomColor());
                        Rectangle r = new Rectangle(30, 30, randomColor());
                        r.setX(x);
                        r.setY(y);
                        root.getChildren().addAll(line, r);

                        PathTransition path = new PathTransition(Duration.seconds(1), line, r);
                        path.setCycleCount(-1);
                        path.setAutoReverse(true);
                        transitions.add(path);
                    }
                    numClicks++;
                    break;
                }
                case SECONDARY: {
                    for (PathTransition p : transitions) {
                        p.play();
                    }
                }
            }
        });

        primaryStage.setTitle("FunnyBox");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Color randomColor() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return Color.rgb(r, g, b);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
