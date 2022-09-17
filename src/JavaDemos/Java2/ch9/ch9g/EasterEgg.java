package JavaDemos.Java2.ch9.ch9g;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Display's an egg. It's width and height can be manipulated using the arrow keys
 * and it can be rotated in either direction using F11 or F12. Clicking on the egg or
 * the background randomizes it's color.
 *
 * @author Jacob Swineford
 */
public class EasterEgg extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        scene.setFill(Color.BEIGE);

        // create a rectangle and an ellipse, fill them, and add them to the scene
        final int width = 400;
        final int height = 300;
        final Rectangle background = new Rectangle(width, height);
        background.setFill(Color.BLACK);

        final int eWidth = 70;
        final int eHeight = 50;
        final Ellipse egg = new Ellipse(eWidth, eHeight);
        egg.setFill(Color.YELLOW);

        root.getChildren().addAll(background, egg);

        // Clicking on either shape randomizes it's color
        class MouseHandler implements EventHandler<MouseEvent> {
            @Override
            public void handle(MouseEvent event) {

//                Color c = randomColor();
//                background.setFill(c);
//                egg.setFill(complement(c));

                Shape shape = (Shape) event.getSource();
                shape.setFill(randomColor());

//                if (event.getSource() == background) {
//                    background.setFill(randomColor());
//                } else {
//                    egg.setFill(randomColor());
//                }
            }
        }
        MouseHandler mouseHandler = new MouseHandler();
        background.setOnMouseClicked(mouseHandler);
        egg.setOnMouseClicked(mouseHandler);
        egg.setFocusTraversable(true);

        // key events are used to stretch and rotate the egg.
        class KeyHandler implements EventHandler<KeyEvent> {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                switch (code) {
                    case UP:
                        egg.setRadiusY(egg.getRadiusY() + 1);
                        break;
                    case DOWN:
                        egg.setRadiusY(egg.getRadiusY() - 1);
                        break;
                    case LEFT:
                        egg.setRadiusX(egg.getRadiusX() - 1);
                        break;
                    case RIGHT:
                        egg.setRadiusX(egg.getRadiusX() + 1);
                        break;
                    case F11:
                        egg.setRotate(egg.getRotate() - 10);
                        break;
                    case F12:
                        egg.setRotate(egg.getRotate() + 10);
                        break;
                    case SPACE:
                        double n = (egg.getRadiusX() + egg.getRadiusY()) / 2;
                        egg.setRadiusX(n);
                        egg.setRadiusY(n);
                }
            }
        }
        //egg.setOnKeyPressed(new KeyHandler());
        scene.setOnKeyPressed(new KeyHandler());

        //primaryStage.setResizable(false);
        primaryStage.setTitle("Easter Egg");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }

    /**
     * Returns a color of a randomly generated RGB Value.
     */
    private static Color randomColor() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return Color.rgb(r, g, b);
    }

    /**
     * Returns the complement of a given color.
     */
    private static Color complement(Color color) {
        double r = 1 - color.getRed();
        double g = 1 - color.getGreen();
        double b = 1 - color.getBlue();
        return new Color(r, g, b, 1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

