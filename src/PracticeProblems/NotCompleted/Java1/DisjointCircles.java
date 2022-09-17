package PracticeProblems.NotCompleted.Java1;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

/**
 * Creates a user specified number of random
 * circles at random locations throughout the pane.
 *
 * These circles never overlap each other nor do
 * they get cut off by the edges of the scene.
 *
 * @author Jacob Swineford
 */
public class DisjointCircles extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        Pane root = new Pane();
        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 1000;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.BLACK);

        System.out.print("Enter the amount of circles: ");
        int cir = in.nextInt();
        int[] colors = new int[3];

        // create the circles at randomly specified coordinates
        for (int i = 0; i < cir; i++) {
            int x = rand.nextInt(SCENE_WIDTH);
            int y = rand.nextInt(SCENE_HEIGHT);
            int radius = rand.nextInt(90) + 10;

//            if (x - radius < 0) {
//                radius -= x;
//            }
//
//            if (x + radius > SCENE_WIDTH) {
//                radius -= x;
//            }
//
//            if (y - radius < 0) {
//                radius -= y;
//            }
//
//            if (y + radius > SCENE_HEIGHT) {
//                radius -= y;
//
//            }


            for (int k = 0; k < 3; k++) {
                int c = rand.nextInt(255);
                colors[k] = c;
            }
            Color rColor = Color.rgb(colors[0], colors[1], colors[2], 1);

            Circle circle = new Circle(x, y, radius);
            circle.setFill(rColor);

            for (Node node : root.getChildren()) {
                Circle d = (Circle) node;
                if (circle.intersects(d.getBoundsInParent())) {

                }
            }

            root.getChildren().add(circle);
        }


        primaryStage.setTitle("Disjoint Circles");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
