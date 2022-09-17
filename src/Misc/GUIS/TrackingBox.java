package Misc.GUIS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Program that demonstrates and provides a box in the middle of the pane that
 * turns and faces the mouse.
 *
 * WARNING: REALLY INEFFICIENT
 *
 * @author Jacob Swineford
 */
public class TrackingBox extends Application {

    private double mouseX;
    private double mouseY;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        TrackerBox box = new TrackerBox(scene);
        box.setTranslateX(SCENE_WIDTH / 3.0);
        Circle origin = new Circle(5, Color.GREEN);
        origin.setCenterX(SCENE_WIDTH / 2.0);
        origin.setCenterY(SCENE_HEIGHT / 2.0);
        root.getChildren().addAll(box, origin);

        scene.setOnMouseMoved(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        });

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Class that consists of a box
     */
    class TrackerBox extends Pane {

        boolean running = true;
        Rotation rotation;

         TrackerBox(Scene scene) {
             Rectangle b = new Rectangle(50, 20, Color.BLUE);
             Circle scope = new Circle(5, Color.RED);
             scope.setCenterX(45);
             scope.setCenterY(10);
             this.getChildren().addAll(b, scope);
             this.setTranslateX(scene.getWidth() / 2 - b.getWidth() / 2);
             this.setTranslateY(scene.getHeight() / 2 - b.getHeight() / 2);
             rotation = new Rotation(this);
             rotation.start();
         }

         void toggleTracking() {
             if (running) {
                 running = false;
                 return;
             }
             running = true;
         }

         class Rotation extends Thread {

             boolean running = true;
             Pane actor;

             Rotation(TrackerBox actor) {
                 this.actor = actor;
             }

             @Override
             public void run() {
                 while (running) {
                     double x = mouseX - actor.getTranslateX();
                     double y = mouseY - actor.getTranslateY();
                     double angle = Math.atan2(y, x);
                     angle = Math.toDegrees(angle);
                     actor.setRotate(angle);
                 }
             }
         }

    }

    public static void main(String[] args) {
        launch(args);
    }


}
