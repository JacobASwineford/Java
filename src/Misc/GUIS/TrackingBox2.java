package Misc.GUIS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

/**
 * Program that provides a set amount of boxes that turn and face the mouse, upon
 * the mouse moving.
 *
 * This version of the tracking box program is much more efficient
 * than the first version.
 *
 * @author Jacob Swineford
 */
public class TrackingBox2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        LinkedList<Pane> boxes = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            Pane box = box(scene, i);
            root.getChildren().add(box);
            boxes.add(box);
        }

        scene.setOnMouseMoved(event -> {
            for (Pane box : boxes)
                trackMouse(event.getX(), event.getY(), box);
        });

        primaryStage.setTitle("TrackingBox2");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Pane box(Scene scene, int v) {
        Pane box = new Pane();
        Rectangle b = new Rectangle(50, 20, Color.BLUE);
        Circle scope = new Circle(5, Color.RED);
        scope.setCenterX(45);
        scope.setCenterY(10);
        box.getChildren().addAll(b, scope);
        box.setTranslateX(scene.getWidth() / v - b.getWidth() / 2);
        box.setTranslateY(scene.getHeight() / v - b.getHeight() / 2);
        return box;
    }

    private void trackMouse(double mouseX, double mouseY, Pane actor) {
        double x = mouseX - actor.getTranslateX();
        double y = mouseY - actor.getTranslateY();
        double angle = Math.atan2(y, x);
        angle = Math.toDegrees(angle);
        actor.setRotate(angle);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
