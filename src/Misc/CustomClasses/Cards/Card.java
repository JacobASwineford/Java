package Misc.CustomClasses.Cards;

public class Card {

    private final int deckValue;

    private final int suite;
    private final int color;
    private final int value;

    /**
     * Assigns the suite, color, value, and deckValue to this Card.
     *
     * @param suite given suite
     * @param color given color
     * @param value given value
     * @param deckValue given deckValue
     */
    public Card(int suite, int color, int value, int deckValue) {
        this.suite = suite;
        this.color = color;
        this.value = value;
        this.deckValue = deckValue;
    }

    /**
     * Formatted String to represent the values represented by this Card object.
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format("[%s (%d) of %s (%d) --- [%s (%d)] {%d}]",
                getValueName(), getValue(), getSuiteName(), getSuite(), getColorName(), getColor(), deckValue);
    }

    /**
     * Gets the suite of this Card.
     *
     * @return suite
     */
    public int getSuite() {return suite;}

    /**
     * Gets the color of this Card.
     * @return color
     */
    public int getColor() {return color;}

    /**
     * Gets the value of this Card.
     *
     * @return value
     */
    public int getValue() {return value;}

    /**
     * Gets the name representation of this Cards suite.
     *
     * @return suite name
     */
    public String getSuiteName() {return Deck.SUITE_NAMES[suite];}

    /**
     * Gets the name representation of this Cards color.
     *
     * @return color name
     */
    public String getColorName() {return Deck.COLOR_NAMES[color];}

    /**
     * Gets the name representation of this Cards value.
     *
     * @return value name
     */
    public String getValueName() {return Deck.VALUE_NAMES[value];}
}
