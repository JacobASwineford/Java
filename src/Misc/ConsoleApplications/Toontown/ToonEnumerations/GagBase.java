package Misc.ConsoleApplications.Toontown.ToonEnumerations;

import static Misc.ConsoleApplications.Toontown.ToonEnumerations.GagCapacityMatrix.*;
import static Misc.ConsoleApplications.Toontown.ToonEnumerations.GagTarget.*;
import static Misc.ConsoleApplications.Toontown.ToonEnumerations.GagTrack.*;

/**
 * Base data affiliated with the gags currently present within Toontown rewritten.
 */
public enum GagBase {
    // Toon-Up gags
    Feather(new int[]{8, 10, 9, 11, 0, 70}, ToonUp, OneToon, NormalProgression),
    Megaphone(new int[]{15, 18, 16, 19, 20, 70}, ToonUp, AllToons, NormalProgression),
    Lipstick(new int[]{25, 30, 27, 33, 200, 70}, ToonUp, OneToon, NormalProgression),
    BambooCane(new int[]{40, 45, 44, 49, 800, 70}, ToonUp, AllToons, NormalProgression),
    PixieDust(new int[]{60, 70, 66, 77, 2000, 70}, ToonUp, OneToon, NormalProgression),
    JugglingBalls(new int[]{90, 120, 99, 132, 6000, 70}, ToonUp, AllToons, NormalProgression),
    HighDive(new int[]{210, 210, 231, 231, 10000, 95}, ToonUp, AllToons, NormalProgression),

    // Trap gags
    BananaPeel(new int[]{10, 12, 11, 13, 0, 0}, Trap, OneEnemy, TrapProgression),
    Rake(new int[]{18, 20, 19, 22, 20, 0}, Trap, OneEnemy, TrapProgression),
    Marbles(new int[]{30, 35, 33, 38, 100, 0}, Trap, OneEnemy, TrapProgression),
    Quicksand(new int[]{45, 50, 49, 55, 800, 0}, Trap, OneEnemy, TrapProgression),
    Trapdoor(new int[]{60, 70, 66, 77, 2000, 0}, Trap, OneEnemy, TrapProgression),
    TNT(new int[]{90, 180, 99, 198, 6000, 0}, Trap, AllEnemies, TrapProgression),
    Railroad(new int[]{200, 200, 220, 220, 10000, 0}, Trap, AllEnemies, TrapProgression),

    // Lure gags
    // base accuracy, organic accuracy, lured rounds
    $1Bill(new int[]{50, 60, 2, 0}, Lure, OneEnemy, NormalProgression),
    SmallMagnet(new int[]{50, 60, 2, 20}, Lure, AllEnemies, NormalProgression),
    $5Bill(new int[]{60, 70, 3, 100}, Lure, OneEnemy, NormalProgression),
    BigMagnet(new int[]{60, 70, 3, 800}, Lure, AllEnemies, NormalProgression),
    $10Bill(new int[]{70, 80, 4, 2000}, Lure, OneEnemy, NormalProgression),
    HypnoGoggles(new int[]{70, 80, 4, 6000}, Lure, AllEnemies, NormalProgression),
    Presentation(new int[]{95, 95, 15, 10000}, Lure, AllEnemies, NormalProgression),

    // Sound gags
    BikeHorn(new int[]{3, 4, 4, 5, 0, 95}, Sound, AllEnemies, NormalProgression),
    Whistle(new int[]{5, 7, 6, 8, 40, 95}, Sound, AllEnemies, NormalProgression),
    Bugle(new int[]{9, 11, 10, 12, 200, 95}, Sound, AllEnemies, NormalProgression),
    Aoogah(new int[]{14, 16, 15, 17, 1000, 95}, Sound, AllEnemies, NormalProgression),
    ElephantTrunk(new int[]{19, 21, 20, 23, 2500, 95}, Sound, AllEnemies, NormalProgression),
    Foghorn(new int[]{25, 50, 27, 55, 7500, 95}, Sound, AllEnemies, NormalProgression),
    OperaSinger(new int[]{90, 90, 99, 99, 10000, 95}, Sound, AllEnemies, NormalProgression),

    // Throw gags
    Cupcake(new int[]{4, 6, 5, 7, 0, 75}, Throw, OneEnemy, NormalProgression),
    FruitPieSlice(new int[]{8, 10, 9, 11, 10, 75}, Throw, OneEnemy, NormalProgression),
    CreamPieSlice(new int[]{12, 17, 13, 18, 50, 75}, Throw, OneEnemy, NormalProgression),
    WholeFruitPie(new int[]{24, 27, 26, 29, 400, 75}, Throw, OneEnemy, NormalProgression),
    WholeCreamPie(new int[]{36, 40, 39, 44, 2000, 75}, Throw, OneEnemy, NormalProgression),
    BirthdayCake(new int[]{48, 100, 52, 110, 6000, 75}, Throw, OneEnemy, NormalProgression),
    WeddingCake(new int[]{120, 120, 132, 132, 10000, 75}, Throw, AllEnemies, NormalProgression),

    // Squirt gags
    SquirtingFlower(new int[]{3, 4, 4, 5, 0, 95}, Squirt, OneEnemy, NormalProgression),
    GlassOfWater(new int[]{6, 8, 7, 9, 10, 95}, Squirt, OneEnemy, NormalProgression),
    SquirtGun(new int[]{10, 12, 11, 13, 50, 95}, Squirt, OneEnemy, NormalProgression),
    SeltzerBottle(new int[]{18, 21, 19, 23, 400, 95}, Squirt, OneEnemy, NormalProgression),
    FireHose(new int[]{27, 30, 29, 33, 2000, 95}, Squirt, OneEnemy, NormalProgression),
    StormCloud(new int[]{37, 80, 40, 88, 6000, 95}, Squirt, OneEnemy, NormalProgression),
    Geyser(new int[]{105, 105, 115, 115, 10000, 95}, Squirt, AllEnemies, NormalProgression),

    // Drop gags
    FlowerPot(new int[]{10, 10, 11, 11, 0, 50}, Drop, OneEnemy, NormalProgression),
    Sandbag(new int[]{18, 18, 19, 19, 20, 50}, Drop, OneEnemy, NormalProgression),
    Anvil(new int[]{30, 30, 33, 33, 100, 50}, Drop, OneEnemy, NormalProgression),
    BigWeight(new int[]{45, 45, 49, 49, 500, 50}, Drop, OneEnemy, NormalProgression),
    Safe(new int[]{60, 60, 66, 66, 2000, 50}, Drop, OneEnemy, NormalProgression),
    GrandPiano(new int[]{85, 170, 93, 187, 6000, 50}, Drop, OneEnemy, NormalProgression),
    Toontanic(new int[]{180, 180, 198, 198, 10000, 50}, Drop, AllEnemies, NormalProgression);

    private final int[] baseValues;
    private final GagTrack track;
    private final GagTarget target;
    private final GagCapacityMatrix capacityMatrix;

    GagBase(int[] baseValues, GagTrack track, GagTarget target, GagCapacityMatrix capacityMatrix) {
        this.baseValues = baseValues;
        this.track = track;
        this.target = target;
        this.capacityMatrix = capacityMatrix;
    }

    /**
     * Returns the base information on the gag that comes after another gag on
     * any particular track.
     *
     * @return base information
     */
    public GagBase after() {
        GagBase[] bases = GagBase.values();

        // get the current location of this base
        int index = 0;
        for (GagBase base : bases) {
            if (this == base)
                break;
            index++;
        }

        if (bases[index].getNeededSkillPoints() == 10000) // level 7 gag, nothing afterwords
            return null;
        return bases[index + 1];
    }

    /**
     * Returns the minimum base damage of this gag.
     *
     * @return minimum base damage
     */
    public int getBaseMinimumDamage() {
        return baseValues[0];
    }

    /**
     * Returns the maximum base damage of this gag.
     *
     * @return maximum base damage
     */
    public int getBaseMaximumDamage() {
        return baseValues[1];
    }

    /**
     * Returns the minimum organic damage of this gag.
     *
     * @return minimum organic damage
     */
    public int getOrganicMinimumDamage() {
        return baseValues[2];
    }

    /**
     * Returns the maximum organic damage of this gag.
     *
     * @return maximum organic damage
     */
    public int getOrganicMaximumDamage() {
        return baseValues[3];
    }

    /**
     * Returns skills points needed to unlock this gag.
     *
     * @return skill points needed
     */
    public int getNeededSkillPoints() {
        return track == Lure ? baseValues[3] : baseValues[4];
    }

    /**
     * Returns the base accuracy pf this gag.
     *
     * @return base accuracy
     */
    public int getBaseAccuracy() {
        return track == Lure ? baseValues[0] : baseValues[5];
    }

    /**
     * Returns the organic accuracy of a lure gag.
     *
     * @return organic accuracy
     */
    public int getLureOrganicAccuracy() {
        return track == Lure ? baseValues[1] : -1;
    }

    /**
     * Returns the number of lure rounds for a lure gag.
     *
     * @return lure rounds
     */
    public int getLureRounds() {
        return track == Lure ? baseValues[2] : -1;
    }

    /**
     * Returns the track of this gag.
     *
     * @return track
     */
    public GagTrack getTrack() {
        return track;
    }

    /**
     * Returns the target of this gag.
     *
     * @return target
     */
    public GagTarget getTarget() {
        return target;
    }

    /**
     * Returns the capacity matrix of this gag.
     *
     * @return capacity matrix
     */
    public GagCapacityMatrix getCapacityMatrix() {
        return capacityMatrix;
    }
}
