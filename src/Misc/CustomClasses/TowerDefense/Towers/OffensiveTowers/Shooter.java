package Misc.CustomClasses.TowerDefense.Towers.OffensiveTowers;

import Misc.CustomClasses.TowerDefense.Game;
import Misc.CustomClasses.TowerDefense.Tiles.Tile;
import Misc.CustomClasses.TowerDefense.Towers.Projectiles.Bullet;
import Misc.CustomClasses.TowerDefense.Towers.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Represents a shooter in this tower defense game.
 */
public class Shooter extends Tower {

    private Timeline activation;

    private double bulletWidth;
    private double bulletHeight;

    /**
     * Overridden method for a shooter tower graphic.
     *
     * @param height given height
     * @param width given width
     *
     * @return shooter tower graphic
     */
    @Override
    public Pane generateGraphic(double width, double height) {
        Pane vis = new Pane();
        vis.setMaxWidth(width);
        vis.setMaxHeight(height);
        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Color.TRANSPARENT);

        double chamberWidth = width / 3;
        Rectangle chamber = new Rectangle(chamberWidth, height);
        chamber.setFill(Color.ALICEBLUE);
        chamber.setStroke(Color.BLACK);

        double barrelWidth = width * .9;
        double barrelHeight = height * .3;
        Rectangle barrel = new Rectangle(barrelWidth, barrelHeight);
        barrel.setFill(Color.LIGHTGREEN);
        barrel.setLayoutY((height - barrelHeight) / 2.0);
        barrel.setLayoutX(width - barrelWidth);
        barrel.setStroke(Color.BLACK);

        double pr = 2;
        Circle dot = new Circle(pr);
        dot.setFill(Color.RED);
        dot.setLayoutX(width);
        dot.setLayoutY(height / 2.0);

        bulletHeight = barrelHeight * .8;
        bulletWidth = bulletHeight * 2;

        vis.getChildren().addAll(bg, chamber, barrel, dot);
        return vis;

    }

    /**
     * Overridden method for offensive tower activation.
     */
    @Override
    public void activate(Tile tile) {
        double rootX = tile.getParent().getLayoutX() + tile.getLayoutX() - Tile.STROKE_WIDTH / 2.0;
        double rootY = tile.getParent().getLayoutY() + tile.getLayoutY() - Tile.STROKE_WIDTH / 2.0;

        double bulletX = rootX + tile.getWidth() - Tile.STROKE_WIDTH;
        double bulletY = rootY + (tile.getHeight() - bulletHeight) / 2;

        // shoot bullets at certain intervals
        KeyFrame kf = new KeyFrame(Duration.millis(500), actionEvent -> {
            Bullet b = new Bullet(bulletWidth, bulletHeight);
            b.setLayoutX(bulletX);
            b.setLayoutY(bulletY);
            Game.ROOT.getChildren().add(b);
            Game.BULLETS.add(b);
        });
        activation = new Timeline(kf);
        activation.setCycleCount(Timeline.INDEFINITE);
        activation.play();
    }

    @Override
    public void deactivate() {
        activation.stop();
    }
}
