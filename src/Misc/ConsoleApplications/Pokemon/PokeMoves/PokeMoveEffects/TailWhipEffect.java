package Misc.ConsoleApplications.Pokemon.PokeMoves.PokeMoveEffects;

import Misc.ConsoleApplications.Pokemon.PokeBattle;
import Misc.ConsoleApplications.Pokemon.PokeData.Pokemon;

import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeBase.defenseIndex;

public class TailWhipEffect extends PokeMoveEffect {

    @Override
    void effect(Pokemon attacking, Pokemon defending, PokeBattle battle) {
        defending.modifyStageMultiplier(defenseIndex, -1);
    }
}
