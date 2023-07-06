package Misc.CustomClasses.BetterUno.Cards;

import Misc.CustomClasses.BetterUno.Color;
import Misc.CustomClasses.BetterUno.UNO;

/**
 * Reverse card in UNO. It has the ability to reverse the turn order.
 */
public class Reverse extends Card {

    public Reverse(Color color) {
        super(NO_VALUE, color);
        precedence = 4;
    }

    @Override
    public void resolve(UNO game) {
        game.setOrder(-game.getOrder());
        game.advance(game.getOrder());
    }

    @Override
    public boolean valid(Card card) {
        return card.color == color || card instanceof Reverse;
    }
}
