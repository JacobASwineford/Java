package Misc.CustomClasses.TowerDefense.Towers.Projectiles;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Represents a bullet from a tower in this tower defense game.
 */
public class Bullet extends Pane {

    private static final Color color = Color.GRAY;

    /**
     * Constructor for a bullet from a tower.
     *
     * @param width given width
     * @param height given height
     */
    public Bullet(double width, double height) {
        this.setMinWidth(width);
        this.setMinHeight(height);
        Rectangle r = new Rectangle(width, height);
        r.setStroke(Color.BLACK);
        r.setStrokeWidth(2);
        r.setFill(color);
        this.getChildren().add(r);

        double mx = 2;
        KeyFrame kf = new KeyFrame(Duration.millis(10), actionEvent -> {
            this.setTranslateX(this.getTranslateX() + mx);
        });

        Timeline t = new Timeline(kf);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }
}
