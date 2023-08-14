package Misc.CustomClasses.TowerDefense.Tiles.OtherTiles;

import Misc.CustomClasses.TowerDefense.Game;
import Misc.CustomClasses.TowerDefense.Tiles.Tile;
import Misc.CustomClasses.TowerDefense.Towers.DefensiveTowers.Defender;
import Misc.CustomClasses.TowerDefense.Towers.OffensiveTowers.DoubleShooter;
import Misc.CustomClasses.TowerDefense.Towers.OffensiveTowers.Shooter;
import Misc.CustomClasses.TowerDefense.Towers.Tower;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a tower selection tile in this tower defense game.
 */
public class TowerSelectionTile extends Tile {

    private static final Color color = Color.LIGHTBLUE;

    private final Tower tower;

    /**
     * Constructor for a tower selection tile.
     *
     * @param width  given width
     * @param height given height
     */
    public TowerSelectionTile(double width, double height, String towerName) {
        super(width, height, color);
        switch (towerName) {
            case "Defender": tower = new Defender(); break;
            case "Shooter": tower = new Shooter(); break;
            case "Double Shooter": tower = new DoubleShooter(); break;
            default:
                System.out.println("Class not supported.");
                tower = new Shooter();
        }
        double towerWidth = width * .7, towerHeight = height * .7;
        Pane tg = tower.generateGraphic(towerWidth, towerHeight);
        System.out.println(width + " " + tg.getWidth());
        tg.setLayoutX((width - tg.getMaxWidth()) / 2);
        tg.setLayoutY((height - tg.getMaxHeight()) / 2);
        this.getChildren().add(tg);

        this.setOnMouseEntered(event -> {
            Rectangle bg = (Rectangle) this.getChildren().get(0);
            bg.setFill(((Color) bg.getFill()).brighter());
        });

        this.setOnMouseExited(event -> {
            Rectangle bg = (Rectangle) this.getChildren().get(0);
            bg.setFill(color);
        });

        this.setOnMouseClicked(mouseEvent -> {
            if (!Game.PLACEMENT_MODE)
                return;
            if (Game.ATTACHED_TOWER != null)
                Game.ROOT.getChildren().remove(Game.ATTACHED);
            Game.ATTACHED = tower.generateGraphic(towerWidth, towerHeight);
            Game.ATTACHED_WIDTH = towerWidth;
            Game.ATTACHED_HEIGHT = towerHeight;
            Game.ATTACHED.setLayoutX(Game.MOUSE_X - towerWidth / 2);
            Game.ATTACHED.setLayoutY(Game.MOUSE_Y - towerHeight / 2);
            Game.ATTACHED.setMouseTransparent(true);
            Game.ROOT.getChildren().add(Game.ATTACHED);
            Game.ATTACHED_TOWER = tower;
        });
    }
}
