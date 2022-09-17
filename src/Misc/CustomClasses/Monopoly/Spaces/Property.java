package Misc.CustomClasses.Monopoly.Spaces;

import Misc.CustomClasses.Monopoly.Enum.PropertyType;
import Misc.CustomClasses.Monopoly.Player;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static Misc.CustomClasses.Monopoly.Enum.PropertyType.*;

/**
 * A Property in Monopoly is the primary source of income for
 * players. Running out of properties and money means the player
 * has to declare bankruptcy.
 *
 * Property can be bought and sold. If a player lands on a
 * property that is not theirs, they have to pay rent. If there
 * are houses/hotels on a property, then the rent due increases.
 *
 * Property can be part of a color set. When all the properties
 * for any particular color are owned, those properties unlock
 * the ability to gain houses/hotels.
 *
 * @author Jacob Swineford
 */
public class Property implements Space {

    private int currentRent;
    private int numBuildings = 0;

    private Player owner;
    private PropertyType propertyType;

    /**
     * Constructs a Property object that keeps track of the current rent
     * and the owner of the property.
     */
    public Property(PropertyType propertyType) {
        this.propertyType = propertyType;
        currentRent = 0;
    }

    /**
     * If the property is owned by another player other than the eventTarget,
     * then the event target has to pay the owner rent.
     */
    @Override
    public void doAutomaticEvent(Player eventTarget) {
        if (owner != null && eventTarget != owner) {
            eventTarget.subtractMoney(currentRent);
        }
    }

    /**
     * Increases the rent of this property, and adds a building. This can only be done
     * if the property is owned.
     * @throws IndexOutOfBoundsException upgrading beyond what is allowed
     */
    public void upgrade() throws IndexOutOfBoundsException {
        int index = findCurrentRentIndex();

        currentRent = propertyType.getRentPrices()[index + 1]; // IOB
        owner.subtractMoney(propertyType.getBuildingCost());

        if (notARailroad()) {
            numBuildings++;
        }
    }

    /**
     * decreases the rent of this property, and removes a building. This
     * can only be done if the property is owned.
     * @throws IndexOutOfBoundsException degrading beyond what is allowed
     */
    public void degrade() throws IndexOutOfBoundsException {
        int index = findCurrentRentIndex();

        currentRent = propertyType.getRentPrices()[index - 1]; // IOB
        owner.addMoney(propertyType.getBuildingMortgageValue());

        if (notARailroad()) {
            numBuildings--;
        }
    }

    /**
     * Buys this property.
     */
    public void buyProperty(Player newOwner) {
        owner = newOwner;
        owner.subtractMoney(propertyType.getPrice());
        currentRent = propertyType.getRentPrices()[numBuildings];
    }

    /**
     * Sells this property, and the houses associated with it.
     */
    public void sellProperty() {
        owner.addMoney(propertyType.getMortgageValue());
        for (int i = 0; i < numBuildings; i++) {
            owner.addMoney(propertyType.getBuildingCost() / 2);
        }
        owner = null;
        numBuildings = 0;
    }

    public int getNumBuildings() {
        return numBuildings;
    }

    public int getCurrentRent() {
        return currentRent;
    }

    public Player getOwner() {
        return owner;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * Returns a pane containing the graphics associated with this property.
     */
    public Pane getPropertySpaceGraphic(double cardWidth, double cardHeight, boolean pb) {
        Pane pane = new Pane();
        double bannerHeight = cardHeight / 5;
        int strokeWidth = 3;
        int fontSize = 10;
        Color strokeColor = Color.BLACK;

        // special properties - images given a "stroke"
        Rectangle stroke = new Rectangle();
        stroke.setWidth(cardWidth);
        stroke.setHeight(cardHeight);
        stroke.setFill(Color.TRANSPARENT);
        stroke.setStroke(strokeColor);
        stroke.setStrokeWidth(strokeWidth);

        if (propertyType == Reading_Railroad) {
            ImageView i;
            i = new ImageView("boardImages/Reading_Railroad.jpg");
            i.setFitHeight(cardHeight);
            i.setFitWidth(cardWidth);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }
        if (propertyType == Pennsylvania_Railroad) {
            ImageView i;
            i = new ImageView("boardImages/Pennsylvania_Railroad.jpg");
            i.setFitHeight(cardHeight);
            i.setFitWidth(cardWidth);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }
        if (propertyType == B_and_O_Railroad) {
            ImageView i;
            i = new ImageView("boardImages/B_and_O_Railroad.jpg");
            i.setFitHeight(cardHeight);
            i.setFitWidth(cardWidth);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }
        if (propertyType == Short_Line) {
            ImageView i;
            i = new ImageView("boardImages/Short_Line.jpg");
            i.setFitHeight(cardHeight);
            i.setFitWidth(cardWidth);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }
        if (propertyType == Electric_Company) {
            ImageView i;
            i = new ImageView("boardImages/Electric_Company.jpg");
            i.setFitHeight(cardHeight);
            i.setFitWidth(cardWidth);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }
        if (propertyType == Water_Works) {
            ImageView i;
            i = new ImageView("boardImages/Water_Works.jpg");
            i.setFitHeight(cardHeight);
            i.setFitWidth(cardWidth);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }

        // otherwise generic property - all share the same design
        Rectangle card = new Rectangle();
        card.setWidth(cardWidth);
        card.setHeight(cardHeight);
        card.setFill(brighterPropertyColor());
        card.setStroke(strokeColor);
        card.setStrokeWidth(strokeWidth);

        Rectangle banner = new Rectangle();
        banner.setWidth(cardWidth);
        banner.setHeight(bannerHeight);
        banner.setFill(propertyType.getColor());
        banner.setStroke(strokeColor);
        banner.setStrokeWidth(strokeWidth);

        FlowPane text = new FlowPane();
        text.setPrefWidth(cardWidth);
        text.setTranslateY(bannerHeight / 2);
        text.setAlignment(Pos.CENTER);

        Font font = Font.font("Monotype", fontSize);

        // property name without underscores, then split and add separately
        String pName = propertyType.toString().replaceAll("_", " ");
        for (String s : pName.split(" ")) {
            Text t = new Text(s + " ");
            t.setTranslateY(bannerHeight);
            t.setFont(font);
            text.getChildren().add(t);
        }

        Text description = new Text("Price: $" + propertyType.getPrice());
        description.setTranslateY(cardHeight / 3);
        description.setFont(font);

        text.getChildren().add(description);

        // PlayerBox generates generic properties without text, for the purposes
        // of fitting them into a FlowPane (text is not resized according to arguments)
        if (pb) {
            pane.getChildren().addAll(card, banner);
        } else {
            pane.getChildren().addAll(card, banner, text);
        }

        return pane;
    }

    public boolean isARailroad() {
        return propertyType == Reading_Railroad || propertyType == B_and_O_Railroad ||
                propertyType == Pennsylvania_Railroad || propertyType == Short_Line;
    }

    public boolean isAUtility() {
        return propertyType == Water_Works || propertyType == Electric_Company;
    }

    private int findCurrentRentIndex() {
        int index = 0;
        for (int i = 0; i < propertyType.getRentPrices().length; i++) {
            if (currentRent == propertyType.getRentPrices()[i]) {
                index = i;
            }
        }
        return index;
    }

    private boolean notARailroad() {
        return propertyType != Reading_Railroad && propertyType != B_and_O_Railroad &&
                propertyType != Pennsylvania_Railroad && propertyType != Short_Line;
    }

    private static final double BRIGHTENER = 200;
    private Color brighterPropertyColor() {
        double red = propertyType.getColor().getRed() * 255;
        double green = propertyType.getColor().getGreen() * 255;
        double blue = propertyType.getColor().getBlue() * 255;
        red += BRIGHTENER;
        green += BRIGHTENER;
        blue += BRIGHTENER;

        if (red > 255) {
            red = 255;
        }
        if (green > 255) {
            green = 255;
        }
        if (blue > 255) {
            blue = 255;
        }

        return Color.rgb((int) red, (int) green, (int) blue, 1);
    }


}
