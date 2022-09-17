package Misc.CustomClasses.Monopoly.Enum;

import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.*;

/**
 * Encompasses the information associated with the various own-able properties
 * in Monopoly.
 *
 * WARNING: UTILITIES AND RAILROADS HAVE SPECIAL RENT PROPERTIES.
 *
 * @author Jacob Swineford
 */
public enum PropertyType {

    Mediterranean_Avenue(BROWN, 60, 30, 50, 25,
            new int[] {2, 10, 30, 90, 160, 250}),

    Baltic_Avenue(BROWN, 60, 30, 50, 25,
            new int[] {4, 20, 60, 180, 320, 450}),

    Oriental_Avenue(LIGHTBLUE, 100, 50, 50, 25,
            new int[] {6, 30, 90, 270, 400, 550}),

    Vermont_Avenue(LIGHTBLUE, 100, 50, 50, 25,
            new int[] {6, 30, 90, 270, 400, 550}),

    Connecticut_Avenue(LIGHTBLUE, 120, 60, 50, 25,
            new int[] {8, 40, 100, 300, 450, 600}),

    StCharles_Place(PINK, 140, 70, 100, 50,
            new int[] {10, 50, 150, 450, 625, 750}),

    States_Avenue(PINK, 140, 70, 100, 50,
            new int[] {10, 50, 150, 450, 625, 750}),

    Virginia_Avenue(PINK, 160, 80, 100, 50,
            new int[] {12, 60, 180, 500, 700, 900}),

    StJames_Place(ORANGE, 180, 90, 100, 50,
            new int[] {14, 70, 200, 550, 750, 950}),

    Tennessee_Avenue(ORANGE, 180, 90, 100, 50,
            new int[] {14, 70, 200, 550, 750, 950}),

    New_York_Avenue(ORANGE, 200, 100, 100, 50,
            new int[] {16, 80, 220, 600, 800, 1000}),

    Kentucky_Avenue(RED, 220, 110, 150, 75,
            new int[] {18, 90, 250, 700, 875, 1050}),

    Indiana_Avenue(RED, 220, 110, 150, 75,
            new int[] {18, 90, 250, 700, 875, 1050}),

    Illinois_Avenue(RED, 240, 120, 150, 75,
            new int[] {20, 100, 300, 750, 925, 1100}),

    Atlantic_Avenue(YELLOW, 260, 130, 150, 75,
            new int[] {22, 110, 330, 800, 975, 1150}),

    Ventnor_Avenue(YELLOW, 260, 130, 150, 75,
            new int[] {22, 110, 330, 800, 975, 1150}),

    Marvin_Gardens(YELLOW, 280, 140, 150, 75,
            new int[] {24, 120, 360, 850, 1025, 1200}),

    Pacific_Avenue(GREEN, 300, 150, 200, 100,
            new int[] {26, 130, 390, 900, 1100, 1275}),

    North_Carolina_Avenue(GREEN, 300, 150, 200, 100,
            new int[] {26, 130, 390, 900, 1100, 1275}),

    Pennsylvania_Avenue(GREEN, 320, 160, 200, 100,
            new int[] {28, 150, 450, 1000, 1200, 1400}),

    Park_Place(DARKBLUE, 350, 175, 200, 100,
            new int[] {35, 175, 500, 1100, 1300, 1500}),

    Boardwalk(DARKBLUE, 400, 200, 200, 100,
            new int[] {50, 200, 600, 1400, 1700, 2000}),

    // Utilities and Railroads have special rent properties
    Electric_Company(GRAY, 150, 75, 0, 0,
            new int[]{}),

    Water_Works(GRAY, 150, 75, 0, 0,
            new int[]{}),

    Reading_Railroad(WHITE, 200, 100, 0, 0,
            new int[]{25, 50, 100, 200}),

    Pennsylvania_Railroad(WHITE, 200, 100, 0, 0,
            new int[]{25, 50, 100, 200}),

    B_and_O_Railroad(WHITE, 200, 100, 0, 0,
            new int[]{25, 50, 100, 200}),

    Short_Line(WHITE, 200, 100, 0, 0,
            new int[]{25, 50, 100, 200});

    private Color color;
    private int price;
    private int mortgageValue;
    private int buildingCost;
    private int buildingMortgageValue;
    private int[] rentPrices;

    PropertyType(Color color, int price, int mortgageValue,
                 int buildingCost, int buildingMortgageValue, int[] rentPrices) {
        this.color = color;
        this.price = price;
        this.mortgageValue = mortgageValue;
        this.buildingCost = buildingCost;
        this.buildingMortgageValue = buildingMortgageValue;
        this.rentPrices = rentPrices;
    }

    public Color getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public int getBuildingCost() {
        return buildingCost;
    }

    public int[] getRentPrices() {
        return rentPrices;
    }

    public int getBuildingMortgageValue() {
        return buildingMortgageValue;
    }
}
