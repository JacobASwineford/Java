package Misc.ConsoleApplications.Pokemon.PokeData;

import Misc.ConsoleApplications.Pokemon.PokeEnumerations.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeBase.*;
import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeGender.*;
import static Misc.ConsoleApplications.Pokemon.PokeEnumerations.PokeGenderRatio.*;

public class Pokemon {

    private String name;

    private int level = 1;

    private final double[] stats;
    private final double[] natureMultipliers;
    private final int[] stageMultipliers;
    private final int[] effortValues;
    private final int[] individualValues;

    private PokeType[] types;
    private PokeNature nature;
    private PokeGender gender;

    Pokemon(PokeBase base) {
        name = base.name();
        stats = new double[6];
        natureMultipliers = new double[6];
        stageMultipliers = new int[6];
        effortValues = new int[6];
        individualValues = new int[6];
        types = new PokeType[2];

        System.arraycopy(base.stats, 0, stats, 0, base.stats.length);
        Arrays.fill(natureMultipliers, 1);
        Arrays.fill(stageMultipliers, 1);
        System.arraycopy(base.types, 0, types, 0, base.types.length);

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        // find random nature and change multipliers
        PokeNature[] natures = PokeNature.values();
        int r = rand.nextInt(natures.length);
        PokeNature nature = natures[r];
        while (nature.decreasedIndex == -1) {
            r = rand.nextInt(natures.length);
            nature = natures[r];
        }
        natureMultipliers[nature.increasedIndex] = 1.1;
        natureMultipliers[nature.decreasedIndex] = .9;

        // randomize IVS
        for (int i = 0; i < individualValues.length; i++)
            individualValues[i] = rand.nextInt(32);

        // randomize nature
        this.nature = PokeNature.values()[rand.nextInt(PokeNature.values().length)];

        // randomize gender, based on gender ratio
        if (base.genderRatio == MaleAll)
            gender = Male;
        else if (base.genderRatio == FemaleAll)
            gender = Female;
        else if (base.genderRatio == UnknownAll)
            gender = Unknown;
        else {
            int male = base.genderRatio.maleFactor;
            int female = base.genderRatio.femaleFactor;
            int bound = 256;
            r = rand.nextInt(bound);
            int portion = bound / (male + female);

            if (r == 255)
                gender = Unknown;
            else if (r == 254)
                gender = Female;
            else if (r == 0 || r <= portion * male)
                gender = Male;
            else
                gender = Female;
        }

    }

    public void damage(double amount) {
        double newHp = stats[hpIndex] - amount;
        if (newHp <= 0)
            stats[hpIndex] = 0;
        stats[hpIndex] = newHp;
    }

    public boolean modifyStageMultiplier(int index, int by) {
        int newMultiplier = stageMultipliers[index] + by;
        if (newMultiplier < -6 || newMultiplier > 6) return false;
        stageMultipliers[index] = newMultiplier;
        return true;
    }

    @Override
    public String toString() {
        double total = stats[hpIndex] + stats[attackIndex] + stats[defenseIndex] + stats[specialDefenseIndex]
                + stats[specialAttackIndex] + stats[speedIndex];
        return String.format("\"%s\" -> \nhp: %.2f\nattack: %.2f\ndefense: %.2f\n" +
                        "Sp. Defense: %.2f\nSp. Attack: %.2f\nSpeed: %.2f\nTotal: %.2f\nlevel: %d\nType 1: %s\nType 2: %s\n" +
                        "Effort values: %s\nIndividual Values: %s\nNature: %s\nGender: %s",
                name, stats[hpIndex], stats[attackIndex], stats[defenseIndex], stats[specialDefenseIndex],
                stats[specialAttackIndex], stats[speedIndex], total, level, types[0], types[1],
                Arrays.toString(effortValues), Arrays.toString(individualValues), nature, gender);
    }

    public static void main(String[] args) {
        PokeBase p = PokeBase.Charmander;
        Pokemon mon = new Pokemon(p);
        System.out.println(mon);
        System.out.println();
        System.out.println();
        int male = p.genderRatio.maleFactor;
        int female = p.genderRatio.femaleFactor;
        double og = 10_000_000;
        int count = (int) og;
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int bound = 256;
        int portion = bound / (male + female);
        System.out.println(portion + " " + male + " " + female);
        int[] genderCount = new int[3];

        int i = 20 | 1000;
        System.out.println("i: " + i);

        int r;
        while (count != 0) {
            switch (p.genderRatio) {
                case MaleAll: {
                    genderCount[0]++;
                    count--;
                    continue;
                }
                case FemaleAll:{
                    genderCount[1]++;
                    count--;
                    continue;
                }
                case UnknownAll: {
                    genderCount[2]++;
                    count--;
                    continue;
                }
            }
            r = rand.nextInt(bound);
            if (male == -1 || r == 255)
                genderCount[2]++;
            else if (r == 254)
                genderCount[1]++;
            else if (r == 0 || r <= portion * male)
                genderCount[0]++;
            else
                genderCount[1]++;
            count--;
        }

        int val = 16;
        System.out.println();
        System.out.println();
        System.out.println("How many 1's are in binary for: " + val + "?");
        int c = 0;
        while (val != 0) {
            if (val % 2 != 0)
                c++;
            val /= 2;
        }
        System.out.println("there are " + c + " 1's in this binary");

        System.out.printf("male: %d [%.1f]\n", genderCount[0], genderCount[0] / og * 100);
        System.out.printf("female: %d [%.1f]\n", genderCount[1], genderCount[1] / og * 100);
        System.out.printf("unknown: %d [%.1f]\n", genderCount[2], genderCount[2] / og * 100);

    }
}
