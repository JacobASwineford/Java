package Misc.CustomClasses.Monopoly.Enum;

import javafx.scene.image.ImageView;

/**
 * @author Jacob Swineford
 */
public enum MiscSpaceType {

    Go(new ImageView("boardImages/Go.jpg")),

    Free_Parking(new ImageView("boardImages/Free_Parking.jpg")),

    Go_to_jail_space(new ImageView("boardImages/Go_to_jail_space.jpg")),

    Luxury_tax(new ImageView("boardImages/Luxury_tax.jpg")),

    Income_Tax(new ImageView("boardImages/Income_tax.jpg")),

    // used more than once, static variable cannot be made
    Community_chest(new ImageView()),
    Chance(new ImageView());

    private ImageView boardImage;

    MiscSpaceType(ImageView boardImage) {
        this.boardImage = boardImage;
    }

    public ImageView getBoardImage() {
        return boardImage;
    }
}
