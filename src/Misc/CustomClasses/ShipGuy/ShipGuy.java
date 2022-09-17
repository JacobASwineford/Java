package Misc.CustomClasses.ShipGuy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Box that shoots other boxes. Revolutionary.
 *
 * IMPORTANT:
 *  THREADS THAT ARE NOT THE MAIN THREAD CANNOT REMOVE OR ADD
 *  TO THE ROOT PANE
 *  THREADS THAT ARE NOT THE MAIN THREAD CAN MANIPULATE NODES
 *  BY CHANGING THEIR TRANSLATION PARAMETERS
 *  THREADS THAT ARE NOT THE MAIN THREAD CAN START TIMELINES
 *  TIMELINES CAN ADD NODES TO THE ROOT NODE
 *
 * @author Jacob Swineford
 */
public class ShipGuy extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 700;
        final int SCENE_HEIGHT = 700;
        double centerX = SCENE_WIDTH / 2.0;
        double centerY = SCENE_WIDTH / 2.0;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        double startX = centerX - (Gun.BODY_WIDTH + Gun.BARREL_WIDTH) / 2;
        double startY = centerY - Gun.BODY_HEIGHT / 2;
        Gun gun = new Gun(root, startX, startY);

        Circle center = new Circle(2, Color.BLUE);
        center.setCenterX(centerX);
        center.setCenterY(centerY);

        root.getChildren().addAll(gun, center);

        scene.setOnMouseMoved(event -> gun.trackMouse(event.getX(), event.getY()));
        scene.setOnMouseClicked(event -> {
            double rise = event.getY() - gun.getTranslateY();
            double run = event.getX() - gun.getTranslateX();

            // work on this? some shots shoot farther
            rise /= 15;
            run /= 15;
            gun.shoot(rise, run);
        });

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case R: gun.move(0, -2); break;
                case G: gun.move(2, 0); break;
                case F: gun.move(0, 2); break;
                case D: gun.move(-2, 0);
            }
        });

        scene.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case R: gun.move(0, 2); break;
                case G: gun.move(-2, 0); break;
                case F: gun.move(0, -2); break;
                case D: gun.move(2, 0);
            }
        });

        primaryStage.setTitle("Shooty Boy");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
