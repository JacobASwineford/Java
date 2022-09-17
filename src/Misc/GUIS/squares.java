package Misc.GUIS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class squares extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        final int SCENE_WIDTH = 500;
        final int SCENE_HEIGHT = 500;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.PINK);

        int d = 50;
        int a = 1; // starts with red
        for (int i = 340; i > 20; i-= d) {
            Rectangle r;
            if (a == 1) {
                r = leave(i, i, true);
            } else {
                r = leave(i, i, false);
            }
            r.setX((SCENE_HEIGHT - i) / 2.0);
            r.setY((SCENE_HEIGHT - i) / 2.0);
            root.getChildren().add(r);
            a = -a;
        }


        primaryStage.setTitle("Squares");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static Rectangle leave(int width, int height,  boolean tilted) {
        Rectangle r = new Rectangle();
        r.setWidth(width);
        r.setHeight(height);
        r.setStroke(Color.BLACK);
        r.setStrokeWidth(4);
        if (tilted) {
            r.setFill(Color.RED);
            r.setRotate(45);
        } else {
            r.setFill(Color.GREEN);
        }
        return r;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
