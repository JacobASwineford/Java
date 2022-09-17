package Homework.Java1.SwinefordHW3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import java.util.Random;

/**
 * Creates an electron cloud. This is done by defining an oval that is 90%
 * the width of the scene and half of that value as the height. Then, it
 * is copied 36 times and rotated by 10 degrees each time to simulate
 * electrons floating around an atom.
 *
 * Changing the number of copies made will allow you to choose the
 * concentration (amount) of electrons shown. the number of ovals shown
 * when trying certain numbers of even numbered electrons might result
 * in some overlap if the even number is nice divisible by 360
 * (but the initial 36 copies works fine, it has overlap either way).
 * Additionally, any number of electrons beyond 360 will result in
 * there only being one oval shown, as the int containing setRotation
 * remains 0.
 *
 * @author Jacob Swineford
 */
public class ElectronCloud extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.BLACK);

        final int ELECTRONS = 361;
        double electronWidth = SCENE_WIDTH * .9 / 2;
        double electronHeight = electronWidth / 2;

        int setRotation = 0;

        for (int i = 0; i < ELECTRONS; i++) {
            Ellipse electronBase = RandomEllipse(electronWidth, electronHeight);
            electronBase.setRotate(setRotation);

            root.getChildren().addAll(electronBase);
            setRotation += (360 / ELECTRONS);
        }

        primaryStage.setTitle("Electron Cloud");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Creates an ellipse which is given a random stroke color, a stroke
     * width of 5, and has a body that is transparent.
     */
    private static Ellipse RandomEllipse(double Width, double Height) {
        Random rand = new Random();
        int r = rand.nextInt(255) + 1;
        int g = rand.nextInt(255) + 1;
        int b = rand.nextInt(255) + 1;
        Color randomColor = Color.rgb(r, g, b, 1);

        Ellipse random = new Ellipse(Width, Height);
        random.setFill(Color.TRANSPARENT);
        random.setStroke(randomColor);
        random.setStrokeWidth(5);

        return random;

    }

    public static void main(String[] args) {
        launch(args);
    }


}
