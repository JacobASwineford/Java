package Misc.ConsoleApplications.Pokemon.PokeEnumerations;

import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeType.*;
import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeGenderRatio.*;

/**
 * File that contains the base stats for Pokemon, including their name and level 1 stats.
 */
public enum PokeBase {

    Charmander(new double[] {39, 52, 43, 60, 50, 65}, SevenOne, new PokeType[] {Fire, null}),
    Bulbasaur(new double[] {45, 49, 49, 65, 65, 45}, SevenOne, new PokeType[] {Grass, Poison}),
    Squirtle(new double[] {44, 48, 65, 50, 64, 43}, SevenOne, new PokeType[] {Water, null});

    public final double[] stats;
    public final PokeGenderRatio genderRatio;
    public final PokeType[] types;

    // for use logging specific values
    public static final int hpIndex = 0;
    public static final int attackIndex = 1;
    public static final int defenseIndex = 2;
    public static final int specialAttackIndex = 3;
    public static final int specialDefenseIndex = 4;
    public static final int speedIndex = 5;

    PokeBase(double[] stats, PokeGenderRatio genderRatio, PokeType[] types) {
        this.stats = stats;
        this.genderRatio = genderRatio;
        this.types = types;
    }
}
