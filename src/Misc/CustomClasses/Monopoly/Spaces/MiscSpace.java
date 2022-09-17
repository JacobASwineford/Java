package Misc.CustomClasses.Monopoly.Spaces;

import Misc.CustomClasses.Monopoly.Enum.MiscSpaceType;
import Misc.CustomClasses.Monopoly.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Misc.CustomClasses.Monopoly.Enum.MiscSpaceType.*;

/**
 * Provides an automatic event for spaces that are neither property nor jail.
 *
 * @author Jacob Swineford
 */
public class MiscSpace implements Space {

    private MiscSpaceType miscSpaceType;

    public MiscSpace(MiscSpaceType miscSpaceType) {
        this.miscSpaceType = miscSpaceType;
    }

    @Override
    public void doAutomaticEvent(Player eventTarget) {
        switch (miscSpaceType) {
            case Go: eventTarget.addMoney(200); break;
            case Luxury_tax: eventTarget.subtractMoney(75); break;
            case Income_Tax: eventTarget.subtractMoney(200);

            // Spaces that rely on the caller
            case Go_to_jail_space: break; // change in location
            case Free_Parking: break; // no effect
            case Community_chest: break; // local event deck
            case Chance: break; // local event deck
        }
    }

    public MiscSpaceType getMiscSpaceType() {
        return miscSpaceType;
    }

    /**
     * Returns an pane containing the graphics associated with this space.
     * Index refers to the multiple (3) copies of Chance and Community_Chest.
     */
    public Pane getMiscSpaceGraphic(double cardWidth, double cardHeight, boolean info) {
        Pane pane = new Pane();

        int strokeWidth = 3;
        Rectangle stroke = new Rectangle();
        stroke.setFill(Color.TRANSPARENT);
        stroke.setStroke(Color.BLACK);
        stroke.setStrokeWidth(strokeWidth);

        // images given a "stroke"
        if (miscSpaceType == Go) {
            ImageView i;
            i = miscSpaceType.getBoardImage();
            i.setFitWidth(cardHeight);
            i.setFitHeight(cardHeight);
            stroke.setWidth(cardHeight);
            stroke.setHeight(cardHeight);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }

        if (miscSpaceType == Go_to_jail_space) {
            ImageView i;
            i = miscSpaceType.getBoardImage();

            i.setFitWidth(cardHeight);
            i.setFitHeight(cardHeight);
            stroke.setWidth(cardHeight);
            stroke.setHeight(cardHeight);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }

        if (miscSpaceType == Free_Parking) {
            ImageView i;
            i = miscSpaceType.getBoardImage();
            i.setFitWidth(cardHeight);
            i.setFitHeight(cardHeight);
            stroke.setWidth(cardHeight);
            stroke.setHeight(cardHeight);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }

        if (miscSpaceType == Luxury_tax) {
            ImageView i;
            i = miscSpaceType.getBoardImage();
            i.setFitWidth(cardWidth);
            i.setFitHeight(cardHeight);
            stroke.setWidth(cardWidth);
            stroke.setHeight(cardHeight);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }

        if (miscSpaceType == Income_Tax) {
            ImageView i;
            i = miscSpaceType.getBoardImage();
            i.setFitWidth(cardWidth);
            i.setFitHeight(cardHeight);
            stroke.setWidth(cardWidth);
            stroke.setHeight(cardHeight);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }

        if (miscSpaceType == Chance) { // 3 different chance spaces
            String url = "boardImages/Chance.jpg";
            ImageView i = new ImageView(url);
            i.setFitWidth(cardWidth);
            i.setFitHeight(cardHeight);
            stroke.setWidth(cardWidth);
            stroke.setHeight(cardHeight);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }

        if (miscSpaceType == Community_chest) { // 3 different Community chests
            String url= "boardImages/Community_Chest.jpg";
            ImageView i = new ImageView(url);
            i.setFitWidth(cardWidth);
            i.setFitHeight(cardHeight);
            stroke.setWidth(cardWidth);
            stroke.setHeight(cardHeight);

            pane.getChildren().addAll(i, stroke);
            return pane;
        }

        return null;
    }
}
