package Misc.CustomClasses.TowerDefense.Tiles.PlacementTiles;

import javafx.scene.paint.Color;

/**
 * Represents a grass tile in this tower defense game.
 */
public class GrassTile extends PlacementTile {

    /**
     * Constructor for a grass tile.
     *
     * @param width given width
     * @param height given height
     */
    public GrassTile(double width, double height) {
        super(width, height, Color.LIGHTGREEN);
    }
}
