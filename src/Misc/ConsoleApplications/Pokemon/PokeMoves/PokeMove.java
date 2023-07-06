package Misc.ConsoleApplications.Pokemon.PokeMoves;

import Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeMoveBase;
import Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeMoveCategory;
import Misc.ConsoleApplications.Pokemon.PokeMoves.PokeMoveEffects.PokeMoveEffect;
import Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeType;

public class PokeMove {

    int pp;
    int power;
    double accuracy;

    PokeType type;
    PokeMoveCategory category;
    PokeMoveEffect effect;

    public PokeMove(PokeMoveBase base) {
        pp = base.pp;
        power = base.power;
        accuracy = base.accuracy;
        type = base.type;
        category = base.category;
        effect = base.effect;
    }
}
