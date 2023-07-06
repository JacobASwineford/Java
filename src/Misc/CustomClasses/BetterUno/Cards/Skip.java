package Misc.CustomClasses.BetterUno.Cards;

import Misc.CustomClasses.BetterUno.Color;
import Misc.CustomClasses.BetterUno.UNO;

/**
 * Skip card in UNO. It has the ability to skip the next player.
 */
public class Skip extends Card {

    public Skip(Color color) {
        super(NO_VALUE, color);
        precedence = 4;
    }

    @Override
    public void resolve(UNO game) {
        game.advance(game.getOrder() * 2);
    }

    @Override
    public boolean valid(Card card) {
        return card.color == color || card instanceof Skip;
    }
}
