package Misc.CustomClasses.TowerDefense.Tiles;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a tile in this tower defense game.
 */
public class Tile extends Pane {

    public static int STROKE_WIDTH = 3;

    /**
     * Constructor for use exclusively within child classes.
     *
     * @param width given width
     * @param height given height
     * @param color given color
     */
    protected Tile(double width, double height, Color color) {
        super();
        this.setWidth(width);
        this.setHeight(height);

        Rectangle bg = new Rectangle();
        bg.setFill(color);
        bg.setWidth(width);
        bg.setHeight(height);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(STROKE_WIDTH);
        this.getChildren().add(bg);
    }
}
