package Misc.CustomClasses.Monopoly.Spaces;

import Misc.CustomClasses.Monopoly.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * @author Jacob Swineford
 */
public class Jail implements Space {

    private ArrayList<Player> listOfPrisoners = new ArrayList<>();

    public Jail() {}

    @Override
    public void doAutomaticEvent(Player eventTarget) {}

    public void addPrisoner(Player player) {
        listOfPrisoners.add(player);
    }

    public void removePrisoner(Player player) {
        listOfPrisoners.add(player);
    }

    public ArrayList<Player> getListOfPrisoners() {
        return listOfPrisoners;
    }

    public static Pane getJailGraphic(double height, boolean info) {
        int strokeWidth = 3;
        Pane p = new Pane();
        p.setPrefWidth(height);
        p.setPrefHeight(height);

        ImageView i;
        if (info) {
            i = new ImageView("infoImages/Jail.jpg");
        } else {
            i = new ImageView("boardImages/Jail.jpg");
        }

        i.setFitWidth(height);
        i.setFitHeight(height);

        Rectangle stroke = new Rectangle();
        stroke.setFill(Color.TRANSPARENT);
        stroke.setStroke(Color.BLACK);
        stroke.setStrokeWidth(strokeWidth);
        stroke.setWidth(height);
        stroke.setHeight(height);

        p.getChildren().addAll(i, stroke);
        return p;
    }

    public boolean isInJail(Player player) {
        return listOfPrisoners.contains(player);
    }
}
