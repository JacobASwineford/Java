package Misc.CustomClasses.TowerDefense.Tiles.PlacementTiles;

import Misc.CustomClasses.TowerDefense.Game;
import Misc.CustomClasses.TowerDefense.Tiles.Tile;
import Misc.CustomClasses.TowerDefense.Towers.DefensiveTowers.Defender;
import Misc.CustomClasses.TowerDefense.Towers.OffensiveTowers.DoubleShooter;
import Misc.CustomClasses.TowerDefense.Towers.OffensiveTowers.Shooter;
import Misc.CustomClasses.TowerDefense.Towers.Tower;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a tile that a tower can be placed on.
 */
public class PlacementTile extends Tile {

    protected Tower tower;
    protected Pane towerGraphic;

    protected Pane shadow;

    protected double width;
    protected double height;
    protected Color color;

    private final double graWidth;
    private final double graHeight;


    /**
     * Constructor for a placement tile.
     *
     * @param width  given width
     * @param height given height
     * @param color given color
     */
    protected PlacementTile(double width, double height, Color color) {
        super(width, height, color);
        this.width = width;
        this.height = height;
        this.color = color;
        graWidth = width * .7;
        graHeight = height * .7;
        handleMouseClicks();
        handleHoverEnter();
        handleHoverExit();
    }

    /**
     * Handles mouse clicks on a placement tile.
     */
    private void handleMouseClicks() {
        this.setOnMouseClicked(mouseEvent -> {
            MouseButton button = mouseEvent.getButton();
            switch (button) {
                case PRIMARY: {
                    if (Game.REMOVAL_MODE && towerGraphic != null) {
                        this.getChildren().remove(towerGraphic);
                        tower.deactivate();
                        tower = null;
                    } else if (Game.PLACEMENT_MODE && Game.ATTACHED_TOWER != null && tower == null) {
                        // store data associated with tower
                        if (Game.ATTACHED_TOWER instanceof Defender)
                            tower = new Defender();
                        else if (Game.ATTACHED_TOWER instanceof Shooter)
                            tower = new Shooter();
                        else // double shooter
                            tower = new DoubleShooter();

                        // generate and add new graphic of tower in tile
                        Pane gra = tower.generateGraphic(graWidth, graHeight);
                        gra.setLayoutX((width - graWidth) / 2);
                        gra.setLayoutY((height - graHeight) / 2);
                        this.getChildren().add(gra);
                        towerGraphic = gra;

                        // sever attached tower from mouse
                        Game.ATTACHED_TOWER = null;
                        Game.ROOT.getChildren().remove(Game.ATTACHED);

                        // activate the current tower, if possible
                        tower.activate(this);
                    }
                    break;
                }
                case SECONDARY: {
                    if (shadow != null) {
                        this.getChildren().remove(shadow);
                        shadow = null;
                    }
                }
            }
        });
    }

    /**
     * Handles when the mouse enters  a placement tile.
     */
    private void handleHoverEnter() {
        this.setOnMouseEntered(event -> {
            Rectangle bg = (Rectangle) this.getChildren().get(0);
            bg.setFill(((Color) bg.getFill()).brighter());

            // place shadow graphic
            if (Game.ATTACHED_TOWER != null && Game.PLACEMENT_MODE && tower == null) {
                Pane gra = Game.ATTACHED_TOWER.generateShadowGraphic(graWidth, graHeight);
                gra.setTranslateX((width - graWidth) / 2);
                gra.setTranslateY((height - graHeight) / 2);
                shadow = gra;
                this.getChildren().add(shadow);
            }
        });
    }

    /**
     * Handles when the mouse exits a placement tile.
     */
    private void handleHoverExit() {
        this.setOnMouseExited(event -> {
            Rectangle bg = (Rectangle) this.getChildren().get(0);
            bg.setFill(color);

            // remove shadow graphic
            if (shadow != null) {
                this.getChildren().remove(shadow);
                shadow = null;
            }
        });
    }
}
