package Misc.CustomClasses.TowerDefense.Towers;

import Misc.CustomClasses.TowerDefense.Tiles.Tile;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Represents a tower in this tower defense game.
 */
public abstract class Tower {

    /**
     * Generates a graphic of any particular tower, given a height and width.
     *
     * @param width given width
     * @param height given height
     *
     * @return tower graphic
     */
    public abstract Pane generateGraphic(double width, double height);

    /**
     * Generates a shadow graphic of any particular tower, given a height
     * and width.
     *
     * @param width given width
     * @param height given height
     *
     * @return tower graphic
     */
    public Pane generateShadowGraphic(double width, double height) {
        Pane ret = generateGraphic(width, height);
        for (Node n : ret.getChildren())
            n.setOpacity(.5);
        return ret;
    }

    /**
     * Activates this tower.
     *
     * @param tile activated from tile
     */
    public abstract void activate(Tile tile);

    /**
     * Deactivates this tower.
     */
    public abstract void deactivate();
}
