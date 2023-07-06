package Misc.CustomClasses.Uno;

public class Card {

    private final int value;
    private Color color;
    private final int drawValue;
    private Effect effect;
    private final Precedence precedence;

    public static final int NO_VALUE = -1;

    public Card(int value, Color color, int drawValue, Precedence precedence) {
        this.value = value;
        this.color = color;
        this.drawValue = drawValue;
        this.precedence = precedence;
        effect = Effect.NO_EFFECT;
    }

    public void addEffect(Effect effect) {this.effect = effect;}

    public Precedence getPrecedence() {return precedence;}

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {this.color = color;}

    public int getDrawValue() {
        return drawValue;
    }

    public Effect getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return String.format("%s {%d, %d} (%s)", color.toString(), value, drawValue, effect.toString());
    }
}
