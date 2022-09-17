package Misc.CustomClasses.SpinLine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a line that can spin with the spin method.
 *
 * @author Jacob Swineford
 */
public class SpinLine extends Line {

    public SpinLine(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public void setSpinStroke(Color color) {
        this.setStroke(color);
    }

    public void setSpinStrokeWidth(double width) {
        this.setStrokeWidth(width);
    }

    public void spin() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        this.setRotate(rand.nextDouble(-45, 45));
    }

}
