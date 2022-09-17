package Misc.CustomClasses.Other;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Jacob Swineford
 */
public class SpinnableDisk {

    private static final String key = "abcdefghijklmnopqrstupwxyz";
    private static double mouseX;

    private double radius;
    private int numCiphers;
    private int numContents;

    public SpinnableDisk(double radius, int numCiphers, int numContents) {
        this.radius = radius;
        this.numCiphers = numCiphers;
        this.numContents = numContents;
    }

    public Pane getCipherDisk() {
        Pane r = new StackPane();
        double dif = radius / (numCiphers + 1);
        double strokeWidth = 3;
        for (int i = 0; i < numCiphers; i++) {
            if (i == numCiphers - 1) { // last spider circle
                r.getChildren().add(getSpiderCircle(numContents,
                        radius - (dif * i), strokeWidth, false));
            } else {
                r.getChildren().add(getSpiderCircle(numContents,
                        radius - (dif * i), strokeWidth, true));
            }
        }
        Circle buffer = new Circle(radius - dif * numCiphers);
        buffer.setFill(Color.WHITE);
        buffer.setStroke(Color.BLACK);
        buffer.setStrokeWidth(2);
        r.getChildren().add(buffer);
        return r;
    }

    public Pane getCipherDisk(double dif, double strokeWidth) {
        Pane r = new Pane();
        for (int i = 0; i < numCiphers; i++) {
            if (i == numCiphers - 1) { // last spider circle
                r.getChildren().add(getSpiderCircle(numContents,
                        radius - (dif * i), strokeWidth, false));
            } else {
                r.getChildren().add(getSpiderCircle(numContents,
                        radius - (dif * i), strokeWidth, true));
            }
        }
        Circle buffer = new Circle(radius - dif * numCiphers);
        buffer.setFill(Color.WHITE);
        buffer.setStroke(Color.BLACK);
        buffer.setStrokeWidth(2);
        r.getChildren().add(buffer);
        return r;
    }

    private static Pane getSpiderCircle(int lines, double radius, double strokeWidth,
                                        boolean spinnable) {
        StackPane sc = new StackPane();
        sc.setMaxHeight(radius);
        sc.setMaxWidth(radius);
        sc.setStyle("-fx-border-color: blue;");

        Circle c = new Circle(radius);
        c.setStroke(Color.BLACK);
        c.setStrokeWidth(strokeWidth);
        c.setFill(Color.WHITE);
        sc.getChildren().add(c);

        double angleRadIncrement = (2 * Math.PI) / lines;
        double curAngle = 0;
        for (int i = 0; i < lines; i++) {
            Line l = slantedLine(curAngle, radius);
            String s = "";
            s += key.toCharArray()[i];
            Text cha = new Text(s);
            cha.setTranslateX(Math.cos(curAngle + 5.2) * radius / 1.1);
            cha.setTranslateY(Math.sin(curAngle + 5.2) * radius / 1.1);
            cha.setFont(new Font(radius / 7));

            curAngle += angleRadIncrement;
            sc.getChildren().addAll(l, cha);
        }

        if (spinnable) {
            sc.setOnMouseDragged(event -> {
                mouseX = event.getSceneX();
                sc.setRotate(mouseX);
            });
        }
        return sc;
    }

    private static Line slantedLine(double curAngle, double radius) {
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(0);
        l.setEndX(Math.cos(curAngle) * radius * 2);
        l.setEndY(Math.sin(curAngle) * radius * 2);
        l.setStroke(Color.BLACK);
        l.setStrokeWidth(2);
        return l;
    }

}
