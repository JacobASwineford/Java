package Misc.ConsoleApplications.Pokemon;

import Misc.ConsoleApplications.Pokemon.PokeData.Pokemon;
import Misc.ConsoleApplications.Pokemon.PokeMoves.PokeMove;

public class PokeBattle {

    private Pokemon pokemon1;
    private Pokemon pokemon2;

    private PokeMove move1;
    private PokeMove move2;

    public PokeBattle(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    public void setMoves(PokeMove move1, PokeMove move2) {
        this.move1 = move1;
        this.move2 = move2;
    }

}
