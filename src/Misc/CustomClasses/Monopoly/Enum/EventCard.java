package Misc.CustomClasses.Monopoly.Enum;

import java.util.ArrayList;

/**
 * Comprised of the types or cards from Community Chest and Chance spaces.
 * The cards from Community Chest and Chance are differentiated via an
 * index, and whether the cards should be playable by the player or not.
 * Having these values provides a modular solution in the event that cards
 * are added/removed.
 *
 * @author Jacob Swineford
 */
public enum EventCard {

    // community chest -- index = 0
    Doctors_fee(0, false), Sale_of_stock(0, false),
    Grand_Opera_night(0, false), Holiday_fund_matures(0, false),
    Income_tax_refund(0, false), It_is_your_birthday(0, false),
    Life_insurance_matures(0, false), Hospital_fee(0, false),
    School_fees(0, false), Consultancy_fee(0, false),
    Street_repairs(0, false), Beauty_Contest(0, false),
    Inherit_100(0, false), Bank_error(0, false),

    // chance -- index = 1
    Advance_to_Illinois_Ave(1, false), Advance_to_StCharles_Place(1, false),
    Advance_to_Utility(1, false), Advance_to_Railroad(1, false),
    Bank_pays_50_dividend(1, false), Go_back_3_spaces(1, false),
    Make_general_repairs(1, false), Pay_poor_tax(1, false),
    Advance_to_Reading_Railroad(1, false), Advance_to_Boardwalk(1, false),
    You_have_been_elected_chairman(1, false), Building_Loan_Matures(1, false),
    Crossword_Competition(1, false),

    // both -- index = 2
    Advance_to_go(2, false), Get_out_of_jail(2, true),
    Go_to_jail(2, false);

    private int index;
    private boolean playable;

    EventCard(int index, boolean playable) {
        this.index = index;
        this.playable = playable;
    }

    public int getIndex() {
        return index;
    }

    public boolean isPlayable() {
        return playable;
    }

    /**
     * Returns an ArrayList of the cards that are playable.
     */
    public ArrayList<EventCard> PlayableCards() {
        ArrayList<EventCard> arr = new ArrayList<>();
        for (EventCard e : EventCard.values()) {
            if (e.isPlayable()) {
                arr.add(e);
            }
        }
        return arr;
    }
}
