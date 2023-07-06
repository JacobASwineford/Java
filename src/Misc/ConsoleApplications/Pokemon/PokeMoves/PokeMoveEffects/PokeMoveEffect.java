package Misc.ConsoleApplications.Pokemon.PokeMoves.PokeMoveEffects;

import Misc.ConsoleApplications.Pokemon.PokeBattle;
import Misc.ConsoleApplications.Pokemon.PokeData.Pokemon;

/**
 * Interface to describe the effect that any particular move has on either
 * the using or defending Pokemon. there is a corresponding move effect for
 * moves that deem it necessary, such as status moves.
 */
public abstract class PokeMoveEffect {

    /**
     * Describes an effect that a Pokemon move has during battle.
     * This method is meant to resolve special effects, not for generic
     * damage calculation. For status effects that do damage overtime,
     * such as poison or bad poison, it's damage will be done after this method is
     * called, along with any weather effects.
     *
     * @param attacking attacking Pokemon
     * @param defending defending Pokemon
     * @param battle current Pokemon battle
     */
    abstract void effect(Pokemon attacking, Pokemon defending, PokeBattle battle);
}
