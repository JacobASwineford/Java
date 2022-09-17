package Misc.CustomClasses.ShipGuy;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Jacob Swineford
 */
class Gun extends Pane {

    private Bullet[] bullets;
    private static int length = 100;

    private int moveX;
    private int moveY;

    private double chamberX;
    private double chamberY;

    private Pane root;

    final static double BODY_WIDTH = 30;
    final static double BODY_HEIGHT = 20;
    final static double BARREL_WIDTH = 15;

    Gun(Pane root, double startX, double startY) {
        this.root = root;
        bullets = new Bullet[length];
        Rectangle body = new Rectangle(BODY_WIDTH, BODY_HEIGHT, Color.WHITE);
        body.setStroke(Color.BLACK);
        body.setStrokeWidth(2);

        double barrelHeight = 10;
        Rectangle barrel = new Rectangle(BARREL_WIDTH, barrelHeight, Color.BLACK);
        barrel.setX(BODY_WIDTH);
        barrel.setY(BODY_HEIGHT / 2 - barrelHeight / 2);

        Circle chamber = new Circle(5, Color.GREEN);
        chamberX = (BODY_WIDTH + BARREL_WIDTH) / 2;
        chamberY = BODY_HEIGHT / 2;
        chamber.setCenterX(chamberX);
        chamber.setCenterY(chamberY);

        this.getChildren().addAll(body, barrel, chamber);
        this.setTranslateX(startX);
        this.setTranslateY(startY);

        // set up the bullet timeline
        KeyFrame kf = new KeyFrame(Duration.millis(10), event -> {
            for (int i = 0; i < length; i++) {
                Bullet b = bullets[i];
                if (b != null) {
                    b.setTranslateX(b.getTranslateX() + b.run);
                    b.setTranslateY(b.getTranslateY() + b.rise);
                    b.airTime += 10;
                    if (b.airTime >= b.killTime) {
                        root.getChildren().remove(b);
                        bullets[i] = null;
                    }
                }

            }
        });

        Timeline t = new Timeline(kf);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

        // set up movement timeline
        KeyFrame kf2 = new KeyFrame(Duration.millis(10), event -> {
            this.setTranslateX(this.getTranslateX() + moveX);
            this.setTranslateY(this.getTranslateY() + moveY);
        });

        Timeline movement = new Timeline(kf2);
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }

    void move(int moveX, int moveY) {
        int sumX = moveX + this.moveX;
        int sumY = moveY + this.moveY;
        if (sumX < 3 && sumX > -3) this.moveX += moveX;
        if (sumY < 3 && sumY > -3) this.moveY += moveY;
    }

    void shoot(double rise, double run) {
        int slot = nextBulletSlot();
        if (slot != -1) {
            double width = 5;
            double height = 5;

            Bullet b = new Bullet(width, height, rise, run, slot);
            b.setX(this.getTranslateX() + chamberX - width / 2);
            b.setY(this.getTranslateY() + chamberY - height / 2);
            b.setFill(Color.BLACK);
            bullets[slot] = b;
        }
    }

    void trackMouse(double mouseX, double mouseY) {
        double x = mouseX - this.getTranslateX();
        double y = mouseY - this.getTranslateY();
        double angle = Math.atan2(y, x);
        angle = Math.toDegrees(angle);
        this.setRotate(angle);
    }

    private int nextBulletSlot() {
        for (int i = 0; i < length; i++) {
            if (bullets[i] == null) return i;
        }
        return -1; // if not available
    }

    private class Bullet extends Rectangle {

        int killTime = 3000;
        int airTime = 0;
        double rise;
        double run;

        // removal
        int bulletIndex;

        Bullet(double width, double height, double rise, double run, int bulletIndex) {
            this.rise = rise;
            this.run = run;
            this.setWidth(width);
            this.setHeight(height);
            this.setFill(Color.BLACK);
            this.bulletIndex = bulletIndex;
            root.getChildren().add(this);
        }
    }
}
