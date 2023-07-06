package Misc.CustomClasses.BetterUno.Cards;

import Misc.CustomClasses.BetterUno.Color;
import Misc.CustomClasses.BetterUno.Player;
import Misc.CustomClasses.BetterUno.UNO;

/**
 * Draw 2 card in UNO. It has the ability to skip the next player
 * and make them draw two cards.
 */
public class Draw2 extends Card {

    public Draw2(Color color) {
        super(NO_VALUE, color);
        precedence = 3;
    }

    @Override
    public void resolve(UNO game) {
        Player next = game.getPlayers().get(game.next(game.getOrder()));
        for (int i = 0; i < 2; i++)
            next.addCard(game.getDeck().draw());
        game.advance(game.getOrder());
    }

    @Override
    public boolean valid(Card card) {
        return card.color == color || card instanceof Draw2;
    }
}
