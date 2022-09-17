package Misc.GUIS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

/**
 * @author Jacob Swineford
 */
public class bars extends Application {

    private static double wid = 1200;
    private static double hei = 800;
    private static double num = 200;
    private static double bwid = wid / num;
    private static Random r = new Random();

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, wid, hei);
        for (int i = 0; i < num; i++) {
            Rectangle r = bar();
            r.setX(i * bwid);
            r.setY(hei - r.getHeight());
            root.getChildren().add(r);
        }
        scene.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY: root.getChildren().clear(); break;
                case SECONDARY:
                    for (int i = 0; i < num; i++) {
                        Rectangle r = bar();
                        r.setX(i * bwid);
                        r.setY(hei - r.getHeight());
                        root.getChildren().add(r);
                    }
                    break;
            }
            });
        primaryStage.setTitle("bars");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static Rectangle bar() {
        Rectangle bar = new Rectangle();
        bar.setWidth(bwid);
        bar.setHeight(r.nextInt((int) hei));
        bar.setFill(Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        bar.setStroke(Color.BLACK);
        bar.setStrokeWidth(4);
        return bar;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
