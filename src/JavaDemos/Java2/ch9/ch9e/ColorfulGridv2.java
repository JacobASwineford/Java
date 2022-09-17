package JavaDemos.Java2.ch9.ch9e;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static javafx.scene.paint.Color.*;

/**
 * Each cell of the 3x3 grid is filled with a unique color selected at random from
 * a small set of possible colors.
 */
public class ColorfulGridv2 extends Application {

    private static final Color[] colors = {
            DARKSLATEBLUE, DEEPPINK, CRIMSON, DARKGOLDENROD,
            ORANGE, PLUM, GRAY, PURPLE, YELLOW, MINTCREAM, GREEN, INDIGO
    };

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        final int size = 1000;
        Scene scene = new Scene(root, size, size, BLACK);

        final int gap = 50; // separation between adjacent cells
        double cellSize = (size - 4 * gap) / 3.0;

        List<Color> listOfColors = new ArrayList<>(Arrays.asList(colors));
        Collections.shuffle(listOfColors);

        // 3x3 grid of square cells
        int rows = 3;
        int cols = 3;
        int colorIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // top-left corner (x,y) of current square
                double x = gap + j * (gap + cellSize);
                double y = gap + i * (gap + cellSize);
                Rectangle cell = new Rectangle(x, y, cellSize, cellSize);

                cell.setFill(listOfColors.get(colorIndex));
                colorIndex++;
                root.getChildren().add(cell);
            }
        }

        primaryStage.setTitle("Colorful grid");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

