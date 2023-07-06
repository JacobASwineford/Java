package Misc.ConsoleApplications.Pokemon.PokeEnumerations;

public enum PokeGenderRatio {
    UnknownAll(-1, -1),
    FemaleAll(0, 1),
    OneSeven(1, 7),
    OneThree(1, 3),
    OneOne(1, 1),
    ThreeOne(3, 1),
    SevenOne(7, 1),
    MaleAll(1, 0);

    public final int maleFactor;
    public final int femaleFactor;

    PokeGenderRatio(int maleFactor, int femaleFactor) {
        this.maleFactor = maleFactor;
        this.femaleFactor = femaleFactor;
    }
}
