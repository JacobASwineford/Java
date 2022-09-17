package Misc.Utilities;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


/**
 * @author Jacob Swineford
 */
public class Transitions {

    public static FillTransition highlight(Color to, Duration seconds, Shape shape) {
        FillTransition f = new FillTransition();
        f.setFromValue((Color) shape.getFill());
        f.setToValue(to);
        f.setDuration(seconds);
        f.setShape(shape);
        f.setAutoReverse(false);
        f.setCycleCount(1);
        return f;
    }

    public static void setEnteredHighlight(Shape shape) {
        Color original = (Color) shape.getFill();
        shape.setOnMouseEntered(event -> {
            FillTransition f = highlight(
                    Color.rgb(200, 0, 0, .6),
                    Duration.millis(100),
                    shape);
            f.play();
        });

        shape.setOnMouseExited(event -> {
            FillTransition f = highlight(
                    original,
                    Duration.millis(100),
                    shape);
            f.play();
        });
    }

}
