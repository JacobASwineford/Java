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
 * Represents a double shooter tower in this tower defense game.
 */
public class DoubleShooter extends Tower {

    private Timeline activation;

    private double bulletWidth;
    private double bulletHeight;

    /**
     * Overridden method for a double shooter tower graphic.
     *
     * @param height given height
     * @param width given width
     *
     * @return double shooter tower graphic
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
        double barrelHeight = height * .2;
        double margin = ((height - (2 * barrelHeight)) / 3);
        Rectangle barrel1 = new Rectangle(barrelWidth, barrelHeight);
        barrel1.setFill(Color.LIGHTGREEN);
        barrel1.setLayoutY(margin);
        barrel1.setLayoutX(width - barrelWidth);
        barrel1.setStroke(Color.BLACK);

        Rectangle barrel2 = new Rectangle(barrelWidth, barrelHeight);
        barrel2.setFill(Color.LIGHTGREEN);
        barrel2.setLayoutY(margin + margin + barrelHeight);
        barrel2.setLayoutX(width - barrelWidth);
        barrel2.setStroke(Color.BLACK);

        bulletHeight = barrelHeight * .8;
        bulletWidth = bulletHeight * 2;

        double pr = 2;
        Circle dot1 = new Circle(pr);
        dot1.setFill(Color.RED);
        dot1.setLayoutX(width);
        dot1.setLayoutY(margin + barrelHeight / 2);

        Circle dot2 = new Circle(pr);
        dot2.setFill(Color.RED);
        dot2.setLayoutX(width);
        dot2.setLayoutY((2 * margin) + barrelHeight + (barrelHeight / 2));

        vis.getChildren().addAll(bg, chamber, barrel1, barrel2, dot1, dot2);
        return vis;
    }

    /**
     * Overridden method for offensive tower activation.
     *
     * @param tile activated from tile
     */
    @Override
    public void activate(Tile tile) {
        double rootX = tile.getParent().getLayoutX() + tile.getLayoutX() + Tile.STROKE_WIDTH / 2.0;
        double rootY = tile.getParent().getLayoutY() + tile.getLayoutY() + Tile.STROKE_WIDTH / 2.0;

        double bullet1X = rootX + tile.getWidth() + Tile.STROKE_WIDTH;
//        double bullet1Y = rootY

        // shoot bullets are certain intervals
        KeyFrame kf = new KeyFrame(Duration.millis(1000), actionEvent -> {
            Bullet b = new Bullet(bulletWidth, bulletHeight);
            b.setLayoutX(rootX + tile.getWidth() - Tile.STROKE_WIDTH);
            b.setLayoutY(rootY + (tile.getHeight() - bulletHeight) / 2);
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
