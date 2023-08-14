package Misc.CustomClasses.TowerDefense.Tiles.OtherTiles;

import Misc.CustomClasses.TowerDefense.Tiles.Tile;
import javafx.scene.paint.Color;

/**
 * Represents a spawn tile in this tower defense game.
 */
public class SpawnTile extends Tile {

    private static final Color color = Color.LIGHTPINK;

    /**
     * Constructor for a spawn tile.
     *
     * @param width  given width
     * @param height given height
     */
    public SpawnTile(double width, double height) {
        super(width, height, color);

        // spaen the enemies
    }
}
