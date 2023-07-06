package Misc.CustomClasses.BetterUno.Cards;

import Misc.CustomClasses.BetterUno.Color;
import Misc.CustomClasses.BetterUno.UNO;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Wild card in UNO. It has the ability to change it's color to whatever
 * it's player decides, of the given valid colors.
 */
public class Wild extends Card {

    public Wild() {
        super(NO_VALUE, null);
        precedence = 2;
    }

    @Override
    public void resolve(UNO game) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        ArrayList<Color> colors = game.validColors();
        int r = rand.nextInt(colors.size());
        color = colors.get(r);
        game.advance(game.getOrder());
    }

    @Override
    public boolean valid(Card card) {
        return true;
    }
}
