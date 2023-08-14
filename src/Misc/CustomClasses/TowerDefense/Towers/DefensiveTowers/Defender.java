package Misc.CustomClasses.TowerDefense.Towers.DefensiveTowers;

import Misc.CustomClasses.TowerDefense.Tiles.Tile;
import Misc.CustomClasses.TowerDefense.Towers.Tower;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents the defender tower in this tower defense game.
 */
public class Defender extends Tower {

    /**
     * Overridden method for a defender tower graphic.
     *
     * @param height given height
     * @param width given width
     *
     * @return defender tower graphic
     */
    @Override
    public Pane generateGraphic(double width, double height) {
        Pane vis = new Pane();
        vis.setMaxWidth(width);
        vis.setMaxHeight(height);
        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Color.TRANSPARENT);

        double vBarWidth = width / 3.2;
        Rectangle vBar = new Rectangle(vBarWidth, height);
        vBar.setFill(Color.ALICEBLUE);
        vBar.setStroke(Color.BLACK);
        vBar.setLayoutX(width - vBarWidth);

        double hBarWidth = width * .7;
        double hBarHeight = height * .2;
        Rectangle hBar1 = new Rectangle(hBarWidth, hBarHeight);
        hBar1.setFill(Color.ALICEBLUE);
        hBar1.setStroke(Color.BLACK);
        hBar1.setLayoutX(width - hBarWidth);

        Rectangle hBar2 = new Rectangle(hBarWidth, hBarHeight);
        hBar2.setFill(Color.ALICEBLUE);
        hBar2.setStroke(Color.BLACK);
        hBar2.setLayoutX(width - hBarWidth);
        hBar2.setLayoutY(height - hBarHeight);

        vis.getChildren().addAll(bg, vBar, hBar1, hBar2);
        return vis;
    }

    @Override
    public void activate(Tile tile) {}

    @Override
    public void deactivate() {}
}
