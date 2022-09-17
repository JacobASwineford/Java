package Misc.GUIS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Jacob Swineford
 */
public class tiles extends Application {

    private static int i = 0;
    private static int l;
    private static int alt = 1;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 1500;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.GRAY);

        double offset = 25;
        double tw = 20;
        double rth = 40; // regular
        double bth = rth - offset; // base
        int numTiles =  SCENE_WIDTH / (int) tw; // number of tiles per row
        Duration duration = Duration.millis(10);

        // each layer will consist regular tiles, except the first one,
        // which will alternate between regular and base tiles.
        int layers = 20; l = 1;

        KeyFrame kf = new KeyFrame(duration, event -> {
            double x = tw * i;
            double y;
            if (alt == 1) {
                y = SCENE_HEIGHT - (rth * l);
                root.getChildren().add(tile(tw, rth, x, y));
            } else  {
                y = SCENE_HEIGHT - (rth * l) + offset;
                if (l == 1) {
                    root.getChildren().add(tile(tw, bth, x, y));
                } else
                    root.getChildren().add(tile(tw, rth, x, y));
            }
            alt = -alt;
            i++;
            if (i == numTiles) {i = 0; alt = 1; l++;}
        });

        Timeline tiles = new Timeline(kf);
        tiles.setCycleCount(numTiles * layers);
        tiles.play();

        primaryStage.setTitle("Tiles Bitch");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static Rectangle tile(double width, double height, double x, double y) {
        int strokeWidth = 3;
        Color stroke = Color.BLACK;
        Rectangle r = new Rectangle();
        r.setWidth(width);
        r.setHeight(height);
        r.setFill(Color.WHITESMOKE);
        r.setStrokeWidth(strokeWidth);
        r.setStroke(stroke);
        r.setX(x);
        r.setY(y);
        return r;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
