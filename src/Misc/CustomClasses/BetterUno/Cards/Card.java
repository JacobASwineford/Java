package Misc.CustomClasses.BetterUno.Cards;

import Misc.CustomClasses.BetterUno.Color;
import Misc.CustomClasses.BetterUno.UNO;

/**
 * Base abstract class for an UNO card.
 */
public abstract class Card {

    protected static int NO_VALUE = -1;

    protected int value;
    protected Color color;

    /**
     * precedence is not set by default, rather by subclasses of
     * Card. Precedence is currently as follows:
     * <p>
     * Number: 5
     * Reverse / Skip: 4
     * Draw 2: 3
     * Wild: 2
     * Wild Draw 4: 1
     */
    protected int precedence;

    /**
     * Constructor for a Card.
     *
     * @param value starting value
     * @param color starting color
     */
    Card(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    /**
     * Resolves the effect of this particular type of card.
     *
     * @param game the game that this card is a part of.
     */
    public abstract void resolve(UNO game);

    /**
     * Checks against the given card to see if this card is valid to play.
     *
     * @param card given card to check against.
     */
    public abstract boolean valid(Card card);

    /**
     * Gets the value of this card.
     *
     * @return the value of this card.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of this card.
     *
     * @param value given value.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the color of this card.
     *
     * @return the color of this card.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this card.
     *
     * @param color given color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the precedence of this card.
     *
     * @return the precedence of this card.
     */
    public int getPrecedence() {return precedence;}
}
