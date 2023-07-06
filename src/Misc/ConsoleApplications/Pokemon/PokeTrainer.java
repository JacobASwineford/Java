package Misc.ConsoleApplications.Pokemon;

import Misc.ConsoleApplications.Pokemon.PokeData.Pokemon;

public class PokeTrainer {

    private String name;

    private final Pokemon[] pokemon;

    public PokeTrainer(String name) {
        this.name = name;
        pokemon = new Pokemon[6];
    }

    public void addPokemon(Pokemon pokemon, int index) {
        this.pokemon[index] = pokemon;
    }

    public void removePokemon(Pokemon pokemon) {
        for (int i = 0; i < this.pokemon.length; i++) {
            if (this.pokemon[i] == pokemon)
                this.pokemon[i] = null;
        }
    }

    public void removePokemon(int index) {
        removePokemon(pokemon[index]);
    }

    public String getName() {return name;}
    public Pokemon[] getPokemon() {return pokemon;}
}
