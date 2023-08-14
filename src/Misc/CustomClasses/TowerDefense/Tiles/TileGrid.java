package Misc.CustomClasses.TowerDefense.Tiles;

import javafx.scene.layout.Pane;

/**
 * Represents a tile grid in this tower defense game.
 */
public class TileGrid extends Pane {

    private final Tile[] tiles;

    private int gridWidth;
    private int gridHeight;

    /**
     * Constructor for this tile grid.
     *
     * @param tilesPerRow number of tiles per row
     * @param tiles tiles to add to this grid
     */
    public TileGrid(int tilesPerRow, Tile[] tiles) {
        double x = 0, y = 0, w, h;
        int c = 0, padding = 3;
        boolean wd = false;
        gridWidth = 0;
        gridHeight = 0;
        this.tiles = tiles;
        for (Tile tile : tiles) {
            tile.setLayoutX(x);
            tile.setLayoutY(y);
            this.getChildren().add(tile);
            c += 1;
            if (c == tiles.length)
                w = tile.getWidth() + Tile.STROKE_WIDTH;
            else
                w = tile.getWidth() + Tile.STROKE_WIDTH + padding;
            x += w;
            if (!wd)
                gridWidth += w;
            if (c % tilesPerRow == 0) {
                if (c == tiles.length)
                    h = 0;
                else
                    h = tile.getHeight() + Tile.STROKE_WIDTH + padding;
                y += h;
                gridHeight += h;
                x = 0;
                wd = true;
            }
        }
    }

    /**
     * Gets the width of the grid.
     *
     * @return width of grid
     */
    public int getGridWidth() {
        return gridWidth;
    }

    /**
     * Gets the height of the grid.
     *
     * @return height of the grid
     */
    public int getGridHeight() {
        return gridHeight;
    }
}
