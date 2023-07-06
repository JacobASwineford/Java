package Misc.ConsoleApplications.Pokemon.PokeEnumerations;

import static Misc.ConsoleApplications.Pokemon.PokeTypes.PokeTypeNames.*;

public enum PokeType {

    Normal(
            normal,
            new int[] {},
            new int[] {rock, steel},
            new int[] {ghost}
    ),

    Fire(
            fire,
            new int[] {grass, ice, bug, steel},
            new int[] {fire, water, rock, dragon},
            new int[] {}
    ),

    Water(
            water,
            new int[] {fire, ground, rock},
            new int[] {water, grass, dragon},
            new int[] {}
    ),

    Electric(
            electric,
            new int[] {water, flying},
            new int[] {electric, grass, dragon},
            new int[] {ground}
    ),

    Grass(
            grass,
            new int[] {water, ground, rock},
            new int[] {fire, grass, poison, flying, bug, dragon, steel},
            new int[] {}
    ),

    Ice(
            ice,
            new int[] {grass, ground, flying, dragon},
            new int[] {fire, water, ice, steel},
            new int[] {}
    ),

    Fighting(
            fighting,
            new int[] {normal, ice, rock, dark, steel},
            new int[] {poison, flying, psychic, bug, fairy},
            new int[] {ghost}
    ),

    Poison(
            poison,
            new int[] {grass, fairy},
            new int[] {poison, ground, rock, ghost},
            new int[] {steel}
    ),

    Ground(
            ground,
            new int[] {fire, electric, poison, rock, steel},
            new int[] {grass, bug},
            new int[] {flying}
    ),

    Flying(
            flying,
            new int[] {grass, fighting, bug},
            new int[] {electric, rock, steel},
            new int[] {}
    ),

    Psychic(
            psychic,
            new int[] {fighting, poison},
            new int[] {psychic, steel},
            new int[] {dark}
    ),

    Bug(
            bug,
            new int[] {grass, psychic, dark},
            new int[] {fire, fighting, poison, flying, ghost, steel, fairy},
            new int[] {}
    ),

    Rock(
            rock,
            new int[] {fire, ice, flying, bug},
            new int[] {fighting, ground, steel},
            new int[] {}
    ),

    Ghost(
            ghost,
            new int[] {psychic, ghost},
            new int[] {dark},
            new int[] {normal}
    ),

    Dragon(
            dragon,
            new int[] {dragon},
            new int[] {steel},
            new int[] {fairy}
    ),

    Dark(
            dark,
            new int[] {psychic, ghost},
            new int[] {fighting, dark, fairy},
            new int[] {}
    ),

    Steel(
            steel,
            new int[] {ice, rock, fairy},
            new int[] {fire, water, electric, steel},
            new int[] {}
    ),

    Fairy(
            fairy,
            new int[] {fighting, dragon, dark},
            new int[] {fire, poison, steel},
            new int[] {}
    );

    private final int typeValue;
    private final int[] superEffective;
    private final int[] notEffective;
    private final int[] ineffective;

    PokeType(int typeValue, int[] superEffective, int[] notEffective, int[] doesNotEffect) {
        this.typeValue = typeValue;
        this.superEffective = superEffective;
        this.notEffective = notEffective;
        this.ineffective = doesNotEffect;
    }

    public double multiplierAgainst(PokeType against) {
        if (has(this.superEffective, against.typeValue))
            return 2;
        else if (has(this.notEffective, against.typeValue))
            return .5;
        else if (has(this.ineffective, against.typeValue))
            return 0;
        return 1;
    }

    private boolean has(int[] arr, int val) {
        for (int in : arr) {
            if (in == val) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Did you know?\n");

        for (PokeType t : PokeType.values()) {
            System.out.printf("%s has the following properties:\n\n", t);
            System.out.println("Super Effective Against:");
            for (int i : t.superEffective)
                System.out.printf("\t%s {x%.2f}\n", PokeType.values()[i], t.multiplierAgainst(PokeType.values()[i]));
            System.out.println("\nNot Very Effective Against:");
            for (int i : t.notEffective)
                System.out.printf("\t%s\n", PokeType.values()[i]);
            System.out.println("\nIneffective Against:");
            for (int i : t.ineffective)
                System.out.printf("\t%s\n", PokeType.values()[i]);
            System.out.println();
            System.out.println();
        }
    }
}
