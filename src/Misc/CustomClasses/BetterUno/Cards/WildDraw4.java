package Misc.CustomClasses.BetterUno.Cards;

import Misc.CustomClasses.BetterUno.Color;
import Misc.CustomClasses.BetterUno.Player;
import Misc.CustomClasses.BetterUno.UNO;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Wild Draw 4 cara in UNO. It has the ability to skip the next player,
 * make them draw 4 cards, and change it's color to whatever it's player
 * decides, of the given valid colors.
 */
public class WildDraw4 extends Card {

    public WildDraw4(Color color) {
        super(NO_VALUE, color);
        precedence = 1;
    }

    @Override
    public void resolve(UNO game) {
        // random color is chosen
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        ArrayList<Color> colors = game.validColors();
        int r = rand.nextInt(colors.size());
        color = colors.get(r);

        // next player draws 4
        Player next = game.getPlayers().get(game.next(game.getOrder()));
        for (int i = 0; i < 4; i++)
            next.addCard(game.getDeck().draw());

        // next player is skipped
        int to = game.getOrder() * 2;
        game.advance(to);
    }

    @Override
    public boolean valid(Card card) {
        return true;
    }
}
