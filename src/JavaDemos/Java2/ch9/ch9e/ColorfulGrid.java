package JavaDemos.Java2.ch9.ch9e;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static javafx.scene.paint.Color.*;

/**
 * Each cell of the 3x3 grid is filled with a unique color selected at random from
 * a small set of possible colors.
 */
public class ColorfulGrid extends Application {

    private static final Color[] colors = {
            DARKSLATEBLUE, DEEPPINK, CRIMSON, DARKGOLDENROD,
            ORANGE, PLUM, GRAY, PURPLE, YELLOW, MINTCREAM, GREEN, INDIGO
    };

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        final int size = 500;
        Scene scene = new Scene(root, size, size, BLACK);

        final int gap = 5; // separation between adjacent cells
        int cellSize = (size - 4 * gap) / 3;

        Set<Color> setOfColors = getColors(9); // sets do not allow duplicates
        Color[] listOfColors = setOfColors.toArray(new Color[]{});

        // 3x3 grid of square cells
        int rows = 3;
        int cols = 3;
        int colorIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // top-left corner (x,y) of current square
                int x = gap + j * (gap + cellSize);
                int y = gap + i * (gap + cellSize);
                Rectangle cell = new Rectangle(x, y, cellSize, cellSize);

                cell.setFill(listOfColors[colorIndex]);
                colorIndex++;
                root.getChildren().add(cell);
            }
        }

        primaryStage.setTitle("Colorful grid");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
    }

    /**
     * Returns a set of distinct colors selected at random from the colors array
     * of this class. Return null of there are not enough colors in the array.
     */
    private static Set<Color> getColors(int n) {
        if (n  >colors.length) {
            return null; // not enough colors
        }
        Set<Color> set = new HashSet<>();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (set.size() < n) {
            int i = rand.nextInt(colors.length);
            set.add(colors[i]); // if the set already has rhe color, then this is ignored
        }
        return set;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
