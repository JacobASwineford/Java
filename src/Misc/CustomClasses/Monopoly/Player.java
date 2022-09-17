package Misc.CustomClasses.Monopoly;

import Misc.CustomClasses.Monopoly.Enum.EventCard;
import Misc.CustomClasses.Monopoly.Spaces.Property;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * A player in the game of Monopoly. A player has money, properties,
 * and playable cards.
 *
 * @author Jacob Swineford
 */
public class Player {

    private int money;
    private String name;
    private String iconURL;
    private boolean bankruptcy;
    private ArrayList<Property> listOfProperties = new ArrayList<>();
    private ArrayList<EventCard> listOfEventCards = new ArrayList<>();
    private int boardIndex = 0;

    Player(int money, String name, String iconURL) {
        this.money = money;
        this.name = name;
        this.iconURL = iconURL;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void subtractMoney(int amount) {
        money -= amount;
    }

    int getMoney() {
        return money;
    }

    void setMoney(int money) {
        this.money = money;
    }

    ArrayList<Property> getListOfProperties() {
        return listOfProperties;
    }

    void addProperty(Property property) {
        listOfProperties.add(property);
    }

    void removeProperty(Property property) {
        listOfProperties.remove(property);
    }

    public ArrayList<EventCard> getListOfEventCards() {
        return listOfEventCards;
    }

    boolean isBankrupt() {
        return bankruptcy;
    }

    public void setBankruptcy(boolean bankruptcy) {
        this.bankruptcy = bankruptcy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
    }

    Pane getIcon(double width, double height, Color backColor) {
        Pane p = new Pane();
        ImageView i = new ImageView(iconURL);
        i.setFitWidth(width);
        i.setFitHeight(height);

        Rectangle b = new Rectangle(width, height);
        b.setFill(backColor);
        b.setStroke(Color.BLACK);
        b.setStrokeWidth(3);
        p.getChildren().addAll(b, i);
        return p;
    }
}
