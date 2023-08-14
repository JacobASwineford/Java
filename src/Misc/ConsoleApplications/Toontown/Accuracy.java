package Misc.ConsoleApplications.Toontown;

/**
 * Calculates the various accuracy values for the game Toontown rewritten,
 * given a certain amount of context.
 */

public class Accuracy {

    public static void main(String[] args) {

    }

    /**
     * The attack accuracy is found by the formula
     * propAcc + trackExp + tgtDef + bonus.
     *
     * @return attack accuracy
     */
    private double atkAcc(Battle battle) {
        return propAcc() + trackExp() + tgtDef() + bonus();
    }

    /**
     * The prop accuracy is the base accuracy of
     * any particular gag.
     *
     * @return prop accuracy
     */
    private double propAcc() {
        return -1;
    }

    /**
     * The track experience determined by the highest
     * level gag in the used track.
     *
     * @return track experience value
     */
    private double trackExp() {
        return -1;
    }

    /**
     * the target defense determined by the targeted cog level.
     * If a gag is used that targets more than one cog, then the
     * defense from the highest level cog is used.
     */
    private double tgtDef() {
        return -1;
    }

    /**
     * The (presumable) sum of the stun bonus and the lured ratio.
     *
     * @return the bonus value
     */
    private double bonus() {
        return prevHits() + luredRatio();
    }

    /**
     * The previous hits on a cog, based on various main and sub conditions.
     * Gags that wish to receive this bonus must meet both main requirements
     * and at least on of the sub conditions.
     * \n
     * Main:
     *  1. The previous attack hit.
     *  2. The previous attack was not of the same track as the current.
     * \n
     * Sub:
     *  1. The previous attack affected the group.
     *  2. The current attack affects the group.
     *  3. The current and previous attacks affect the same target.
     *
     * @return previous hits value
     */
    private double prevHits() {
        return -1;
    }

    /**
     * The lured ratio is given to all gags that hit the entire group.
     * ([number of cogs lured]) / ([total cogs in battle]) * 100.
     *
     * @return lured ratio value
     */
    private double luredRatio() {
        return -1;
    }


}
