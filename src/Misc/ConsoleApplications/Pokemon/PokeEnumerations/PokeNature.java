package Misc.ConsoleApplications.Pokemon.PokeEnumerations;

import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeBase.*;

public enum PokeNature {
    Hardy(-1, -1),
    Lonely(attackIndex, defenseIndex),
    Brave(attackIndex, speedIndex),
    Adamant(attackIndex, specialAttackIndex),
    Naughty(attackIndex, specialDefenseIndex),
    Bold(defenseIndex, attackIndex),
    Docile(-1, -1),
    Relaxed(defenseIndex, speedIndex),
    Impish(defenseIndex, specialAttackIndex),
    Lax(defenseIndex, specialDefenseIndex),
    Timid(speedIndex, attackIndex),
    Hasty(speedIndex, defenseIndex),
    Serious(-1, -1),
    Jolly(speedIndex, specialAttackIndex),
    Naive(speedIndex, specialDefenseIndex),
    Modest(specialAttackIndex, attackIndex),
    Mild(specialAttackIndex, defenseIndex),
    Quiet(specialAttackIndex, speedIndex),
    Bashful(-1, -1),
    Rash(specialAttackIndex, specialDefenseIndex),
    Calm(specialDefenseIndex, attackIndex),
    Gentle(specialDefenseIndex, defenseIndex),
    Sassy(specialDefenseIndex, speedIndex),
    Careful(specialDefenseIndex, specialAttackIndex),
    Quirky(-1, -1);

    public final int increasedIndex;
    public final int decreasedIndex;

    PokeNature(int increasedIndex, int decreasedIndex) {
        this.increasedIndex = increasedIndex;
        this.decreasedIndex = decreasedIndex;
    }
}
