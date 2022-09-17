package Misc.CustomClasses.SpinLine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Game that allows the user to put 5 lines in specified locations by clicking.
 * Upon putting down these 5 lines, subsequent clicks to the root pane will
 * randomly spin all of them.
 *
 * @author Jacob Swineford
 */
public class GUI extends Application {

    private static final int NUM_LINES = 5;
    private SpinLine[] spinLines = new SpinLine[NUM_LINES];
    private int spinLineIndex = 0;

    private int counter;

    // 0 -> x, 1 -> y
    private double[] start = new double[2];
    private double[] end = new double[2];

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 700;
        final int SCENE_HEIGHT = 700;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.LIGHTBLUE);
        scene.setOnMouseClicked(event -> {
            if (counter < 10) {
                if (counter % 2 == 0) { // start
                    start[0] = event.getX();
                    start[1] = event.getY();
                    counter++;
                } else { // end, render line
                    end[0] = event.getX();
                    end[1] = event.getY();
                    SpinLine sl = new SpinLine(start[0], start[1], end[0], end[1]);

                    ThreadLocalRandom rand = ThreadLocalRandom.current();
                    int red = rand.nextInt(256);
                    int green = rand.nextInt(256);
                    int blue = rand.nextInt(256);
                    Color random = Color.rgb(red, green, blue);
                    sl.setSpinStroke(random);
                    sl.setSpinStrokeWidth(5);
                    root.getChildren().add(sl);
                    spinLines[spinLineIndex++] = sl;
                    counter++;
                }
            } else {
                for (SpinLine sl : spinLines) {
                    sl.spin();
                }
            }
        });

        primaryStage.setTitle("Spin Lines");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
