package Misc.ConsoleApplications.Pokemon.PokeEnumerations;

import Misc.ConsoleApplications.Pokemon.PokeMoves.PokeMoveEffects.PokeMoveEffect;

import Misc.ConsoleApplications.Pokemon.PokeMoves.PokeMoveEffects.*;
import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeMoveCategory.*;
import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeType.*;

public enum PokeMoveBase {

    Tackle(25, 40, 100, Normal, Physical, null),
    TailWhip(30, 0, 100, Normal, Status, new TailWhipEffect());

    public final int pp;
    public final int power;
    public final double accuracy;

    public final PokeType type;
    public final PokeMoveCategory category;
    public final PokeMoveEffect effect;

    PokeMoveBase(int pp, int power, double accuracy, PokeType type, PokeMoveCategory category, PokeMoveEffect effect) {
        this.pp = pp;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.category = category;
        this.effect = effect;
    }
}
