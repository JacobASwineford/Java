package Misc.CustomClasses.TowerDefense.Tiles.OtherTiles;

import Misc.CustomClasses.TowerDefense.Game;
import Misc.CustomClasses.TowerDefense.Tiles.Tile;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a removal tile in this tower defense game.
 */
public class RemovalTile extends Tile {

    private static final Color color = Color.DARKRED.brighter();

    /**
     * Constructor for a removal tile.
     *
     * @param width  given width
     * @param height given height
     */
    public RemovalTile(double width, double height) {
        super(width, height, color);

        double useW = width * .7;
        double useH = height * .7;
        Rectangle use = new Rectangle(useW, useH);
        use.setFill(color.darker());
        use.setLayoutX((width - useW) / 2);
        use.setLayoutY((height - useH) / 2);

        this.setOnMouseEntered(mouseEvent -> {
            Rectangle bg = (Rectangle) this.getChildren().get(0);
            bg.setFill(((Color) bg.getFill()).brighter());
        });

        this.setOnMouseExited(mouseEvent -> {
            Rectangle bg = (Rectangle) this.getChildren().get(0);
            bg.setFill(color);
        });

        this.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() != MouseButton.PRIMARY)
                return;

            if (Game.REMOVAL_MODE) {
                this.getChildren().remove(use);
                Game.toPlacementMode();
            } else {
                this.getChildren().add(use);
                Game.toRemovalMode();
            }
        });
    }
}
