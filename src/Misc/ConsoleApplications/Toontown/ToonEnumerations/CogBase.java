package Misc.ConsoleApplications.Toontown.ToonEnumerations;

import static Misc.ConsoleApplications.Toontown.ToonEnumerations.CogType.*;

/**
 * Base data affiliated the cogs currently present within Toontown rewritten.
 */
public enum CogBase {
    // Bossbots
    Flunky(Bossbot),
    PencilPusher(Bossbot),
    Yesman(Bossbot),
    Micromanager(Bossbot),
    Downsizer(Bossbot),
    HeadHunter(Bossbot),
    CorporateRaider(Bossbot),
    TheBigCheese(Bossbot),
    ChiefExecutiveOfficer(Bossbot),

    // Lawbots
    BottomFeeder(Lawbot),
    Bloodsucker(Lawbot),
    DoubleTalker(Lawbot),
    AmbulanceChaser(Lawbot),
    BackStabber(Lawbot),
    SpinDoctor(Lawbot),
    LegalEagle(Lawbot),
    BigWig(Lawbot),
    ChiefJustice(Lawbot),

    // Cashbots
    ShortChange(Cashbot),
    PennyPincher(Cashbot),
    Tightwad(Cashbot),
    BeanCounter(Cashbot),
    NumberCruncher(Cashbot),
    MoneyBags(Cashbot),
    LoanShark(Cashbot),
    RobberBaron(Cashbot),
    ChiefFinancialOfficer(Cashbot),

    // Sellbots
    ColdCaller(Sellbot),
    Telemarketer(Sellbot),
    NameDropper(Sellbot),
    GladHandler(Sellbot),
    MoverAndShaker(Sellbot),
    TwoFace(Sellbot),
    TheMingler(Sellbot),
    MrHollywood(Sellbot),
    VicePresident(Sellbot),

    // Special
    TheBoiler(Special);

    private final CogType type;

    CogBase(CogType type) {
        this.type = type;
    }

    /**
     * Returns the type of this cog.
     *
     * @return type
     */
    public CogType getType() {
        return type;
    }
}
