package Misc.CustomClasses.BetterUno.Cards;

import Misc.CustomClasses.BetterUno.Color;
import Misc.CustomClasses.BetterUno.UNO;

/**
 * Number card in UNO. it has no special effects.
 */
public class Number extends Card {

    public Number(int value, Color color) {
        super(value, color);
        precedence = 5;
    }

    @Override
    public void resolve(UNO game) {
        game.advance(game.getOrder());
    }

    @Override
    public boolean valid(Card card) {
        return card.color == color || card.value == value;
    }
}
